package com.vnici.postal.biz.request;

import com.vnici.postal.biz.entity.Point;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "判断坐标请求类")
public class PolygonRequest {

    @ApiModelProperty(value = "需要检查是否在多边形内的点",required = true)
    //用于存储需要检查是否在多边形内的点
    private List<Point> points;

    @ApiModelProperty(value = "用于存储多个多边形",required = false)
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
