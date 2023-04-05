package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：请假
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Data
public class Leave {
    public Integer id;
    public String username;
    public String name;
    public String leave_type;
    public String start;
    public String end;
    public String reason;
    public String leave_yn;
    public String emergency_tel;
    public String remarks;
    public String status;
}