package com.vnici.postal.biz.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Destination {
    Point point;

//    Map<String,String> result;

    List<String> result;
}
