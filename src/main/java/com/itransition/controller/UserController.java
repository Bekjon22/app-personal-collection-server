package com.itransition.controller;

import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.resp.UserIdsDto;
import com.itransition.payload.resp.UserRespDto;
import org.springframework.web.bind.annotation.*;

import static com.itransition.utils.AppConstant.*;

/**
 * @author Bekjon Bakhromov
 * @since 05.07.2022
 */
@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {
    String USER_CONTROLLER = BASE_PATH + "/user";

    @GetMapping("get-all")
    ApiResult<CustomPage<UserRespDto>> getAll(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
                                                     @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size);

    @PutMapping("/block")
    ApiResult<?> blockUsers(@RequestBody UserIdsDto ids);

    @PutMapping("/unblock")
    ApiResult<?> unblockUsers(@RequestBody UserIdsDto ids);

    @DeleteMapping("/delete")
    ApiResult<?> delete(@RequestBody UserIdsDto ids);

    @PutMapping("/add-admin")
    ApiResult<?> addAdmin(@RequestBody UserIdsDto ids);

    @PutMapping("/remove-admin")
    ApiResult<?> removeAdmin(@RequestBody UserIdsDto ids);



}
