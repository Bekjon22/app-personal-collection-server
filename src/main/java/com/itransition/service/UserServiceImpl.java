package com.itransition.service;

import com.itransition.common.MessageService;
import com.itransition.entity.Role;
import com.itransition.entity.User;
import com.itransition.enums.RoleEnum;
import com.itransition.exception.RestException;
import com.itransition.mapper.UserMapper;
import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.resp.UserIdsDto;
import com.itransition.payload.resp.UserRespDto;
import com.itransition.repository.RoleRepository;
import com.itransition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Bekjon Bakhromov
 * @since 05.07.2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public ApiResult<CustomPage<UserRespDto>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("firstName")));
        Page<User> allUsers = userRepository.findAll(pageable);
        return ApiResult.successResponse(makeCustomPage(allUsers));
    }

    @Override
    public CustomPage<UserRespDto> makeCustomPage(Page<User> users) {
        List<UserRespDto> userRespDtos = new ArrayList<>();
        for (User user : users.getContent()) {
            UserRespDto userRespDto = new UserRespDto();
            userRespDto.setId(user.getId());
            userRespDto.setFirstName(user.getFirstName());
            userRespDto.setEmail(user.getEmail());
            userRespDto.setRegisterAt(user.getCreatedAt());
            userRespDto.setRoleName(user.getRole().getName());
            userRespDto.setStatus(user.isEnabled());
            userRespDtos.add(userRespDto);
        }
        return new CustomPage<>(
                userRespDtos,
                users.getNumberOfElements(),
                users.getNumber(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.getSize()
        );
    }

    @Override
    public ApiResult<?> block(UserIdsDto ids) {
        List<User> allUsers = userRepository.findAll();
        for (Integer id : ids.getIds()) {
            for (User allUser : allUsers) {
                if (allUser.getId().equals(id)) {
                    if (allUser.getRole().getType().equals(RoleEnum.ROLE_ADMIN)){
                        throw RestException.restThrow(MessageService.getMessage("ADMIN_CANNOT_BLOCK"),HttpStatus.BAD_REQUEST);
                    }
                    allUser.setEnabled(false);
                    userRepository.save(allUser);
                }
            }
        }
        return ApiResult.successResponse(MessageService.getMessage("BLOCKED"));
    }

    @Override
    public ApiResult<?> unblock(UserIdsDto ids) {
        List<User> allUsers = userRepository.findAll();
        for (Integer id : ids.getIds()) {
            for (User allUser : allUsers) {
                if (allUser.getId().equals(id)) {
                    allUser.setEnabled(true);
                    userRepository.save(allUser);
                }
            }
        }
        return ApiResult.successResponse(MessageService.getMessage("UNBLOCKED"));
    }

    @Override
    public ApiResult<?> delete(UserIdsDto ids) {
        for (Integer idi : ids.getIds()) {
            Optional<User> user = userRepository.findById(idi);
            User user1 = user.get();
            if (user1.getRole().getType().equals(RoleEnum.ROLE_ADMIN)){
                throw RestException.restThrow(MessageService.getMessage("ADMIN_CANNOT_DELETE"),HttpStatus.BAD_REQUEST);
            }
            userRepository.delete(user1);
        }
        return ApiResult.successResponse(MessageService.getMessage("DELETED"));
    }

    @Override
    public ApiResult<?> addAdmin(UserIdsDto ids) {
        Role role = roleRepository.findByType(RoleEnum.ROLE_ADMIN).orElseThrow(() -> RestException.notFound(MessageService.getMessage("ROLE_NOT_FOUND")));

        for (Integer idi : ids.getIds()) {
            Optional<User> user = userRepository.findById(idi);
            User user1 = user.get();
            user1.setRole(role);
            userRepository.save(user1);
        }
        return ApiResult.successResponse(MessageService.getMessage("SET_ROLE_ADMIN"));

    }

    @Override
    public ApiResult<?> removeAdmin(UserIdsDto ids) {

        Role role = roleRepository.findByType(RoleEnum.ROLE_USER).orElseThrow(() -> RestException.notFound(MessageService.getMessage("ROLE_NOT_FOUND")));
        Role role1 = roleRepository.findByType(RoleEnum.ROLE_ADMIN).orElseThrow(() -> RestException.notFound(MessageService.getMessage("ROLE_NOT_FOUND")));
        for (Integer idi : ids.getIds()) {

            Integer integer = userRepository.countByRole(role1);
            if (integer<=1){
                throw RestException.restThrow(MessageService.getMessage("ADMIN_ROLE_NOT_CHANGED"), HttpStatus.BAD_REQUEST);
            }

            Optional<User> user = userRepository.findById(idi);
            User user1 = user.get();
            user1.setRole(role);
            userRepository.save(user1);
        }
        return ApiResult.successResponse(MessageService.getMessage("SET_ROLE_USER"));
    }


}
