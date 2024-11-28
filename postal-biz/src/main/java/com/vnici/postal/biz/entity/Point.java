package com.vnici.postal.biz;

import lombok.Data;

@Data
public class Point {

    //经度 longitude
    private double lng;

    //纬度 latitude
    private double lat;

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
