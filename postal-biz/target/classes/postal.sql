create database postal;
CREATE TABLE postal_area (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
     area_id int UNIQUE COMMENT '区域id',
     area_name varchar(100) NOT NULL COMMENT '区域名',
     area_position text NOT NULL COMMENT '区域轮廓坐标',
     mark_color varchar(50) NOT NULL COMMENT '区域轮廓颜色'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;