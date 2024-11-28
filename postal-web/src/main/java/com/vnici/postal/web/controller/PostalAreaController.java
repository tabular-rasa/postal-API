package com.vnici.postal.web.controller;


import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.request.AreaAddRequest;
import com.vnici.postal.service.api.PostalAreaService;
import com.vnici.postal.web.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/postal/")
@RestController
@Slf4j
public class PostalAreaController {

    @Resource
    PostalAreaService postalAreaService;
    @RequestMapping(value = "/addArea",method = RequestMethod.POST)
    public Response<Integer> addArea(@RequestBody AreaAddRequest request) {
        try {
            Integer ret = postalAreaService.addPostalArea(request);
            return Response.success(ret);
        } catch (Exception e) {
            log.error("新增区域失败",e);
            return Response.error(null);
        }
    }

    @RequestMapping(value = "/removeArea/{id}",method = RequestMethod.GET)
    public Response<Integer> removeArea(@PathVariable Integer id) {
        try {
            Integer ret = postalAreaService.removePostalArea(id);
            return Response.success(ret);
        } catch (Exception e) {
            log.error("删除区域失败",e);
            return Response.error(null);
        }
    }

    @RequestMapping(value = "/getAreas",method = RequestMethod.GET)
    public Response<List<PostalArea>> getAreas() {
        try {
            List<PostalArea> allAreas = postalAreaService.getAllAreas();

            return Response.success(allAreas);
        } catch (Exception e) {
            log.error("获取区域失败",e);
            return Response.error(null);
        }
    }
}
