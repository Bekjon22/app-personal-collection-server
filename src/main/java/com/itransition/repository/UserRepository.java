package com.itransition.repository;

import com.itransition.entity.Role;
import com.itransition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @created 23.06.2022-2:56 PM
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User>findByEmail(String email);

    boolean existsByEmail(String email);


//    @Query(value = "select count(r) from users u\n" +
//            "inner join roles r on r.id = u.role_id\n" +
//            "where r.type='ROLE_ADMIN'",nativeQuery = true)

   Integer countByRole(Role role);


}
