package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：返校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Data
public class Back {
    public Integer id;
    public String username;
    public String name;
    public String plan_go;
    public String plan_reach;
    public String becity;
    public String emergency_tel;
    public String evidences;
    public String remarks;
    public String status;
}