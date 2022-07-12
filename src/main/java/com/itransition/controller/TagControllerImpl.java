package com.itransition.controller;

import com.itransition.payload.ApiResult;
import com.itransition.payload.resp.TagRespDtoProjection;
import com.itransition.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */
@RestController
@RequiredArgsConstructor
public class TagControllerImpl implements TagController{

    private final TagService tagService;

    @Override
    public ApiResult<List<TagRespDtoProjection>> tagDropdown(String text) {
        return tagService.getTagValues(text);
    }
}
