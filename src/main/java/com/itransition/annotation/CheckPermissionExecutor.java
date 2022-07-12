package com.itransition.annotation;

import com.itransition.common.MessageService;
import com.itransition.entity.User;
import com.itransition.exception.RestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */
@Component
@Aspect
public class CheckPermissionExecutor {


    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exist = true;
                break;
            }
        }
        if (!exist){
            throw RestException.forbidden(MessageService.getMessage("NO_PERMISSION"),checkPermission.value());
        }

    }
}
