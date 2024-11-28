package com.vnici.postal.service.impl;

import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.mapper.PostalAreaDao;
import com.vnici.postal.biz.request.AreaAddRequest;
import com.vnici.postal.service.api.PostalAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostalAreaServiceImpl implements PostalAreaService {

    @Resource
    PostalAreaDao postalAreaDao;

    @Override
    public Integer addPostalArea(AreaAddRequest request) {
        PostalArea postalArea = new PostalArea();
        BeanUtils.copyProperties(request, postalArea);
        postalAreaDao.addPostalArea(postalArea);
        postalArea.setAreaId(postalArea.getId());
        return postalAreaDao.updatePostalAreaById(postalArea);
    }

    @Override
    public Integer removePostalArea(Integer id) {
        return postalAreaDao.removePostalAreaById(id);
    }

    @Override
    public List<PostalArea> getAllAreas() {
        return postalAreaDao.getAllPostalAreas();
    }
}
