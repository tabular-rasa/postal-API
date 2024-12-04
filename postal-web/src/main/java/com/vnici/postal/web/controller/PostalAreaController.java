package com.vnici.postal.web.controller;


import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.request.AreaAddRequest;
import com.vnici.postal.service.api.PostalAreaService;
import com.vnici.postal.web.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Api(tags = "区域的增删改查")
@RequestMapping("/postal/")
@RestController
@Slf4j
public class PostalAreaController {

    @Resource
    PostalAreaService postalAreaService;
    @ApiOperation(value = "添加区域")
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
    @ApiOperation(value = "根据区域id删除区域")
    @RequestMapping(value = "/removeArea/{id}",method = RequestMethod.GET)
    public Response<Integer> removeArea(@ApiParam(value = "区域id")@PathVariable Integer id) {
        try {
            Integer ret = postalAreaService.removePostalArea(id);
            return Response.success(ret);
        } catch (Exception e) {
            log.error("删除区域失败",e);
            return Response.error(null);
        }
    }
    @ApiOperation(value = "获取所有区域的坐标点集合")
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
