package com.ty.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("clip_record")
public class ClipRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private LocalDateTime createTime;
}
