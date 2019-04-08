use zero;

CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `app_name` varchar(45) NOT NULL COMMENT 'App名称',
  `app_version` varchar(30) NOT NULL COMMENT 'App版本号',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除(0-否;1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App信息';


use sama;

CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `app_name` varchar(45) NOT NULL COMMENT 'App名称',
  `app_version` varchar(30) NOT NULL COMMENT 'App版本号',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除(0-否;1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App信息';


use sharding0;

CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `app_name` varchar(45) NOT NULL COMMENT 'App名称',
  `app_version` varchar(30) NOT NULL COMMENT 'App版本号',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除(0-否;1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App信息';


use sharding1;

CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `app_name` varchar(45) NOT NULL COMMENT 'App名称',
  `app_version` varchar(30) NOT NULL COMMENT 'App版本号',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除(0-否;1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App信息';


