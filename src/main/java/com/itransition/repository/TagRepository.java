package com.itransition.repository;

import com.itransition.entity.Item;
import com.itransition.entity.Tag;
import com.itransition.payload.resp.TagRespDtoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author Bekjon Bakhromov
 * @since 30.06.2022
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {

    List<Tag> findAllByItem(Item item);

    @Query(value = " select t.value from tags t\n " +
            " where t.value ilike ?1" +
            " group by t.value ",nativeQuery = true)
    List<TagRespDtoProjection> getAllByText(String text);

}
