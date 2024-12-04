package com.vnici.postal.biz.entity;

import lombok.Data;

@Data
public class Point {

    //经度 longitude
    double lng;

    //纬度 latitude
    double lat;

    public Point(double lng, double lat) {
        this.lat = lat;
        this.lng = lng;
    }

    public Point(){

    }
}
