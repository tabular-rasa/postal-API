package com.vnici.postal.service.api;


import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.request.AreaAddRequest;

import java.util.List;

public interface PostalAreaService {

    Integer addPostalArea(AreaAddRequest request);

    Integer removePostalArea(Integer id);

    List<PostalArea> getAllAreas();

}
