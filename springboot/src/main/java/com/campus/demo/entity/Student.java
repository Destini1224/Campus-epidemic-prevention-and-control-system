package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：学生
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2022/12/19
 */

@Data
public class Student {
    public Integer id;
    public String username;
    //     @JsonIgnore 忽略某个字段，不展示给前端
    public String password;
    public String token;
    public String name;
    public String stuClass;
    public String dormitory;
    public String tel;
    public String email;
    public String address;
    public Integer status;
}
