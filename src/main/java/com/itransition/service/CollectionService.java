package com.itransition.service;

import com.itransition.entity.Collection;
import com.itransition.entity.User;
import com.itransition.enums.Topic;
import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.req.CollectionDto;
import com.itransition.payload.req.CollectionEditDto;
import com.itransition.payload.resp.CollectionProjection;
import com.itransition.payload.resp.CollectionResDto;
import com.itransition.payload.resp.CollectionResDtoAll;
import com.itransition.payload.resp.UserRespDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * @author Bekjon Bakhromov
 * @since  23.06.2022-1:06 PM
 */
public interface CollectionService {

    ApiResult<?> add(CollectionDto dto,User user) ;

    ApiResult<CollectionResDto> edit(Integer id, CollectionEditDto dto);

    ApiResult<?> delete(Integer id);

    ApiResult<List<CollectionProjection>> getTop();

    CustomPage<CollectionResDtoAll> makeCustomPage(Page<Collection> collections);

    ApiResult<List<CollectionResDto>> getAllByUser(User user);


    ApiResult<CustomPage<CollectionResDtoAll>> getAll(Integer page, Integer size);
}
