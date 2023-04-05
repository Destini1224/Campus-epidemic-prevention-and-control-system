package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：物资申请
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Data
public class Application {
    public Integer id;
    public String username;
    public String name;
    public String goods_name;
    public String application;
    public String remarks;
    public String status;
}