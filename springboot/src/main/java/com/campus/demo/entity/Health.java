package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：健康信息
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/21
 */
@Data
public class Health {
    public Integer id;
    public String username;
    public String name;
    public Float temperature;
    public String address;
    public String health;
    public String contact_infection;
    public String remarks;
    public String u_type;
    public String last_time;
}