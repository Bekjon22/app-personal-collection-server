package com.itransition.service;

import com.itransition.entity.User;
import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.resp.UserIdsDto;
import com.itransition.payload.resp.UserRespDto;
import org.springframework.data.domain.Page;

/**
 * @author Bekjon Bakhromov
 * @since 05.07.2022
 */
public interface UserService {

    ApiResult<CustomPage<UserRespDto>> getAll(Integer page, Integer size);

    CustomPage<UserRespDto> makeCustomPage(Page<User> users);

    ApiResult<?> block(UserIdsDto ids);

    ApiResult<?> unblock(UserIdsDto ids);

    ApiResult<?> delete(UserIdsDto ids);

    ApiResult<?> addAdmin(UserIdsDto ids);

    ApiResult<?> removeAdmin(UserIdsDto ids);
}
