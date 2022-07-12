package com.itransition.controller;

import com.itransition.annotation.CheckPermission;
import com.itransition.entity.User;
import com.itransition.enums.Topic;
import com.itransition.payload.ApiResult;
import com.itransition.payload.req.CollectionDto;
import com.itransition.payload.req.CollectionEditDto;
import com.itransition.payload.resp.CollectionProjection;
import com.itransition.payload.resp.CollectionResDto;
import com.itransition.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


/**
 * @author Bekjon Bakhromov
 * @since 23.06.2022-1:04 PM
 */
@RestController
@RequiredArgsConstructor
public class CollectionControllerImpl implements CollectionController {

    private final CollectionService collectionService;

//    @CheckPermission(value = "CREATE_COLLECTION")
    @PreAuthorize(value = "hasAnyAuthority('CREATE_COLLECTION')")
    @Override
    public ApiResult<?> addCollection(CollectionDto dto, User user) {
        return collectionService.add(dto, user);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_COLLECTION')")
    @Override
    public ApiResult<CollectionResDto> editCollection(Integer id, CollectionEditDto dto) {
        return collectionService.edit(id, dto);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_COLLECTION')")
    @Override
    public ApiResult<?> delete(Integer id) {
        return collectionService.delete(id);
    }


    @Override
    public ApiResult<List<CollectionProjection>> getTopCollections() {
        return collectionService.getTop();
    }



    @PreAuthorize(value = "hasAuthority('GET_MY_COLLECTION')")
    @Override
    public ApiResult<List<CollectionResDto>> getAllByUser(User user) {
        return collectionService.getAllByUser(user);
    }


}
