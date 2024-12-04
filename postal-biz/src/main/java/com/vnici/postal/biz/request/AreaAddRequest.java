package com.vnici.postal.biz.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "添加区域请求类")
public class AreaAddRequest {
    @ApiModelProperty(value = "区域名",required = true)
    String areaName;
    @ApiModelProperty(value = "区域坐标点集合",required = true)
    String areaPosition;
    @ApiModelProperty(value = "区域边框颜色",required = true)
    String markColor;

}
