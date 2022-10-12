package com.demo.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbo
 * @date 2022/7/28 17:45
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("studentinfo")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("name")
    private String name;
    @TableField("age")
    private int age;
    @TableField("address")
    private  String address;
}
