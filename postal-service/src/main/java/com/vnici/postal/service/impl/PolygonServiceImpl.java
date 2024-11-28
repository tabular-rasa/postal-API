package com.vnici.postal.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vnici.postal.biz.entity.Destination;
import com.vnici.postal.biz.entity.Point;
import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.mapper.PostalAreaDao;
import com.vnici.postal.biz.request.PolygonRequest;
import com.vnici.postal.service.api.PolygonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolygonServiceImpl implements PolygonService {
    @Override
    public List<Point> getPolygonBounds(List<Point> points) {
        int pointLen = points.size();
        Point top = points.get(0);
        Point down = points.get(0);
        Point left = points.get(0);
        Point right = points.get(0);

        // 找出多边形的上下左右边界
        for (int i = 1; i < pointLen; i++) {
            Point current = points.get(i);
            if (current.getLng() > top.getLng()) {
                top = current;
            } else if (current.getLng() < down.getLng()) {
                down = current;
            }

            if (current.getLat() > right.getLat()) {
                right = current;
            } else if (current.getLat() < left.getLat()) {
                left = current;
            }
        }

        // 创建外包矩形的四个顶点
        List<Point> polygonBounds = new ArrayList<>();
        polygonBounds.add(new Point(top.getLng(), left.getLat()));
        polygonBounds.add(new Point(top.getLng(), right.getLat()));
        polygonBounds.add(new Point(down.getLng(), right.getLat()));
        polygonBounds.add(new Point(down.getLng(), left.getLat()));

        return polygonBounds;
    }

    @Override
    public Boolean isPointInRect(Point point, List<Point> polygonBounds) {
        return point.getLng() >= polygonBounds.get(3).getLng() &&
                point.getLng() <= polygonBounds.get(0).getLng() &&
                point.getLat() >= polygonBounds.get(3).getLat() &&
                point.getLat() <= polygonBounds.get(2).getLat();
    }

    @Override
    public List<Point> isPointsInPolygons(List<Point> pointList, List<List<Point>> polygonSet) {
        List<Point> inpolygonList = new ArrayList<>();

        // 遍历每个多边形
        for (List<Point> points : polygonSet) {
            // 获取多边形的外包矩形
            List<Point> polygonBounds = getPolygonBounds(points);

            // 遍历每个点
            for (Point point : pointList) {
                // 如果点不在外包矩形内，跳过
                if (!isPointInRect(point, polygonBounds)) {
                    continue;
                }

                int pointLen = points.size();
                //多边形 起始判断点
                Point p1 = points.get(0);
                boolean flag = false;

                // 使用射线法判断点是否在多边形内
                for (int i = 1; i < pointLen; i++) {
                    Point p2 = points.get(i);

                    // 如果点与多边形的顶点重合，直接认为在多边形内
                    if ((point.getLng() == p1.getLng() && point.getLat() == p1.getLat()) ||
                            (point.getLng() == p2.getLng() && point.getLat() == p2.getLat())) {
                        inpolygonList.add(point);
                        break;
                    }

                    // 判断线段两端是否在射线两侧
                    if ((p2.getLat() < point.getLat() && p1.getLat() >= point.getLat()) ||
                            (p1.getLat() < point.getLat() && p2.getLat() >= point.getLat())) {
                        double x;
                        // 该线段是否与射线水平

                        x = p2.getLng() - (p2.getLat() - point.getLat()) * (p2.getLng() - p1.getLng()) / (p2.getLat() - p1.getLat());

                        // 如果点在多边形的边上，直接认为在多边形内
                        if (x == point.getLng()) {
                            inpolygonList.add(point);
                            break;
                        }

                        // 如果射线穿过多边形的边界，切换标志
                        if (x > point.getLng()) {
                            flag = !flag;
                        }
                    }

                    p1 = p2;
                }

                // 如果标志为真，说明点在多边形内
                if (flag) {
                    inpolygonList.add(point);
                }
            }
        }

        return inpolygonList;
    }

    @Override
    public List<Destination> isPointsInArea(PolygonRequest request) {
        List<Point> pointList = request.getPoints();

        List<Destination> resultList = new ArrayList<>();
        // 在区域里的点
        List<Point> inpolygonList = new ArrayList<>();

        // 多个待判断区域
        List<PostalArea> allPostalAreas = getAllPostalAreas();
        // 遍历每个多边形
        for (Point point : pointList) {
            Destination destination = new Destination();
            destination.setPoint(point);
            List<String> result = new ArrayList<>();
            for (PostalArea postalArea : allPostalAreas) {

                List<Point> points = postalArea.getPoints();
                // 获取多边形的外包矩形
                List<Point> polygonBounds = getPolygonBounds(points);

                // 遍历每个点

                    // 如果点不在外包矩形内，跳过
                    if (!isPointInRect(point, polygonBounds)) {
                        continue;
                    }

                    int pointLen = points.size();
                    //多边形 起始判断点
                    Point p1 = points.get(0);
                    boolean flag = false;

                    // 使用射线法判断点是否在多边形内
                    for (int i = 1; i < pointLen; i++) {
                        Point p2 = points.get(i);

                        // 如果点与多边形的顶点重合，直接认为在多边形内
                        if ((point.getLng() == p1.getLng() && point.getLat() == p1.getLat()) ||
                                (point.getLng() == p2.getLng() && point.getLat() == p2.getLat())) {
                            inpolygonList.add(point);
                            break;
                        }

                        // 判断线段两端是否在射线两侧
                        if ((p2.getLat() < point.getLat() && p1.getLat() >= point.getLat()) ||
                                (p1.getLat() < point.getLat() && p2.getLat() >= point.getLat())) {
                            double x;
                            // 该线段是否与射线水平

                            x = p2.getLng() - (p2.getLat() - point.getLat()) * (p2.getLng() - p1.getLng()) / (p2.getLat() - p1.getLat());

                            // 如果点在多边形的边上，直接认为在多边形内
                            if (x == point.getLng()) {
                                inpolygonList.add(point);
                                break;
                            }

                            // 如果射线穿过多边形的边界，切换标志
                            if (x > point.getLng()) {
                                flag = !flag;
                            }
                        }

                        p1 = p2;
                    }

                    // 如果标志为真，说明点在多边形内
                    if (flag) {
                        inpolygonList.add(point);
                        result.add(postalArea.getAreaName());
                    }
                }
            destination.setResult(result);
            resultList.add(destination);
            }
        return resultList;
    }

    @Resource
    PostalAreaDao postalAreaDao;

    List<PostalArea> getAllPostalAreas(){
        List<PostalArea> allPostalAreas = postalAreaDao.getAllPostalAreas();
        for (PostalArea postalArea : allPostalAreas) {
            String areaPosition = postalArea.getAreaPosition();
            Gson gson = new Gson();
            List<Point> points = gson.fromJson(areaPosition, new TypeToken<List<Point>>() {}.getType());
            postalArea.setPoints(points);
        }
        return allPostalAreas;
    }
}
