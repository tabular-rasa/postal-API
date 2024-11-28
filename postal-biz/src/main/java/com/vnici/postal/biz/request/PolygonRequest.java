package com.vnici.postal.biz.request;

import com.vnici.postal.biz.entity.Point;
import lombok.Data;
import java.util.List;

@Data
public class PolygonRequest {

    //用于存储需要检查是否在多边形内的点
    private List<Point> points;

   //用于存储多个多边形，每个多边形是一个 List<Point>，其中包含多边形的顶点坐标
    private List<List<Point>> polygons;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<List<Point>> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<List<Point>> polygons) {
        this.polygons = polygons;
    }
}
