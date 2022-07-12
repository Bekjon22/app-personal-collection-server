package com.itransition.payload.resp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */

public interface TagRespDtoProjection extends Serializable {
    String getValue();

}
