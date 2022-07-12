package com.itransition.controller;

import com.itransition.entity.User;
import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.resp.UserIdsDto;
import com.itransition.payload.resp.UserRespDto;
import com.itransition.repository.UserRepository;
import com.itransition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 05.07.2022
 */
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{
    private final UserService userService;

    @PreAuthorize(value = "hasAuthority('GET_USERS')")
    @Override
    public ApiResult<CustomPage<UserRespDto>> getAll(Integer page, Integer size) {
        return userService.getAll(page,size);
    }

    @PreAuthorize(value = "hasAuthority('BLOCK_USERS')")
    @Override
    public ApiResult<?> blockUsers(UserIdsDto ids) {
        return userService.block(ids);
    }

    @PreAuthorize(value = "hasAuthority('UNBLOCK_USERS')")
    @Override
    public ApiResult<?> unblockUsers(UserIdsDto ids) {
        return userService.unblock(ids);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USERS')")
    @Override
    public ApiResult<?> delete(UserIdsDto ids) {
        return userService.delete(ids);
    }

    @PreAuthorize(value = "hasAuthority('ADD_ADMIN')")
    @Override
    public ApiResult<?> addAdmin(UserIdsDto ids) {
        return userService.addAdmin(ids);
    }

    @PreAuthorize(value = "hasAuthority('REMOVE_ADMIN')")
    @Override
    public ApiResult<?> removeAdmin(UserIdsDto ids) {
        return userService.removeAdmin(ids);
    }
}
