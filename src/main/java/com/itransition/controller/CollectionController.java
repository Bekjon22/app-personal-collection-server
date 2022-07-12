package com.itransition.controller;

import com.itransition.annotation.CurrentUser;
import com.itransition.entity.User;
import com.itransition.enums.Topic;
import com.itransition.payload.ApiResult;
import com.itransition.payload.CustomPage;
import com.itransition.payload.req.CollectionDto;
import com.itransition.payload.req.CollectionEditDto;
import com.itransition.payload.resp.CollectionProjection;
import com.itransition.payload.resp.CollectionResDto;
import com.itransition.payload.resp.CollectionResDtoAll;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.itransition.utils.AppConstant.*;

/**
 * @author Bekjon Bakhromov
 * @since  23.06.2022-11:03 AM
 */
@RequestMapping(CollectionController.COLLECTION_CONTROLLER)
public interface CollectionController {

    String COLLECTION_CONTROLLER = BASE_PATH + "/collection";

    @PostMapping("/add")
    ApiResult<?> addCollection(@RequestBody @Valid CollectionDto dto, @CurrentUser User user);

    @PutMapping("{id}")
    ApiResult<CollectionResDto> editCollection(@PathVariable(name = "id") Integer id,@RequestBody @Valid CollectionEditDto dto);

    @DeleteMapping("{id}")
    ApiResult<?> delete(@PathVariable(name = "id") Integer id);

    @GetMapping("/get-top")
    ApiResult<List<CollectionProjection>> getTopCollections();

    @GetMapping("/get-all-by-user")
    ApiResult<List<CollectionResDto>>getAllByUser(@CurrentUser User user);

    @GetMapping("/get-all")
    ApiResult<CustomPage<CollectionResDtoAll>>getAll(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
                                                     @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size);



}
