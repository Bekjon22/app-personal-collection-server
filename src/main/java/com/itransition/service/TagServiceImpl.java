package com.itransition.service;

import com.itransition.exception.RestException;
import com.itransition.payload.ApiResult;
import com.itransition.payload.resp.TagRespDtoProjection;
import com.itransition.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public ApiResult<List<TagRespDtoProjection>> getTagValues(String text) {
        List<TagRespDtoProjection> allByText = tagRepository.getAllByText("%".concat(text).concat("%"));

        return ApiResult.successResponse(allByText);
    }
}
