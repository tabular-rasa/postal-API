package com.vnici.postal.service.api;


import com.vnici.postal.biz.entity.Destination;
import com.vnici.postal.biz.entity.Point;
import com.vnici.postal.biz.request.PolygonRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

public interface PolygonService {

    /**
     * 计算多边形的外包矩形
     * @param points 单个多边形的顶点合集
     * @return 包围多边形的矩形的上下左右边界
     */
    List<Point> getPolygonBounds(List<Point> points);
    /**
     *
     * @param point 待判断点
     * @param polygonBounds 矩形上下左右边界
     * @return 结果
     */
    Boolean isPointInRect(Point point, List<Point> polygonBounds);

    /**
     * 判断点集中的点是否在多边形集中的任意一个多边形内
     * @param pointList 待判断的点
     * @param polygonSet 多个多边形的顶点合集
     * @return
     */
    List<Point> isPointsInPolygons(List<Point> pointList, List<List<Point>> polygonSet);

    /**
     * 从数据库中获得轮廓数据 返回区域名
     * @return
     */
    List<Destination> isPointsInArea(PolygonRequest request);
}
