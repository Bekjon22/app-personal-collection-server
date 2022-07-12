package com.itransition.controller;

import com.itransition.payload.ApiResult;
import com.itransition.payload.resp.TagRespDtoProjection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;

import static com.itransition.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */
@RequestMapping(TagController.TAG_CONTROLLER)
public interface TagController {
    String TAG_CONTROLLER = BASE_PATH + "/tag";

    @PostMapping("/search/{text}")
    ApiResult<List<TagRespDtoProjection>> tagDropdown(@PathVariable(name = "text") String text);
}
