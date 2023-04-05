package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：离校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Data
public class Departure {
    public Integer id;
    public String username;
    public String name;
    public String plan_leave;
    public String plan_reach;
    public String destination;
    public String emergency_tel;
    public String plan_back;
    public String evidences;
    public String remarks;
    public String status;
}