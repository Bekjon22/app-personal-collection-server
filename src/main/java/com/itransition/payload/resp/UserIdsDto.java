package com.itransition.payload.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 05.07.2022
 */
@Getter
@Setter
public class UserIdsDto {
    private List<Integer> ids;
}
