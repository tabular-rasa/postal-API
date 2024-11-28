package com.vnici.postal.biz.mapper;

import com.vnici.postal.biz.entity.PostalArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostalAreaDao {
    Integer addPostalArea(PostalArea postalArea);

    Integer updatePostalAreaById(PostalArea postalArea);

    List<PostalArea> getAllPostalAreas();

    Integer removePostalAreaById(Integer id);
}
