package com.vnici.postal.biz.entity;

import lombok.Data;

import java.util.List;

@Data
public class PostalArea {
    int id;
    int areaId;
    String areaName;
    String areaPosition;
    String markColor;
    List<Point> points;

}
