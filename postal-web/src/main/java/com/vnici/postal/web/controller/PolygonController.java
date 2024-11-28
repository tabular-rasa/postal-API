package com.vnici.postal.web.controller;


import com.vnici.postal.biz.entity.Destination;
import com.vnici.postal.biz.entity.Point;
import com.vnici.postal.biz.request.AreaAddRequest;
import com.vnici.postal.biz.request.PolygonRequest;
import com.vnici.postal.service.api.PolygonService;
import com.vnici.postal.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/polygon/")
@RestController
@Slf4j
public class PolygonController {

    @Resource
    PolygonService polygonService;

    @RequestMapping(value = "/checkPoints",method = RequestMethod.POST)
    public Response<List<Point>> checkPoints(@RequestBody PolygonRequest request) {
        try {
            // 从请求中获取点集和多边形集
            List<Point> xyset = request.getPoints();
            List<List<Point>> polygonset = request.getPolygons();
            // 调用方法判断哪些点在多边形内，并返回结果
            List<Point> pointsInPolygons = polygonService.isPointsInPolygons(xyset, polygonset);
            return Response.success(pointsInPolygons);
        } catch (Exception e) {
            log.error("判断多边形失败",e);
            return Response.error(null);
        }
    }

    // 根据数据库中的区域判断
    @RequestMapping(value = "/checkPoints_v2",method = RequestMethod.POST)
    public Response<List<Destination>> checkPointsV2(@RequestBody PolygonRequest request) {
        try {

            // 调用方法判断哪些点在多边形内，并返回结果
            List<Destination> pointsInArea = polygonService.isPointsInArea(request);
            return Response.success(pointsInArea);
        } catch (Exception e) {
            log.error("判断多边形失败",e);
            return Response.error(null);
        }
    }

}
