package com.itransition.service;

import com.itransition.payload.ApiResult;
import com.itransition.payload.resp.TagRespDtoProjection;

import java.util.List;
import java.util.Set;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */
public interface TagService {

    ApiResult<List<TagRespDtoProjection>> getTagValues(String text);
}
