package com.campus.demo.entity;

import lombok.Data;

/**
 * Description：管理员
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */

@Data
public class Admin {
    public Integer id;
    public String username;
    //     @JsonIgnore 忽略某个字段，不展示给前端
    public String password;
    public String token;
    public Integer status;
}
