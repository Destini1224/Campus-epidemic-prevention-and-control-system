package com.campus.demo.service;

import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Admin;
import com.campus.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：管理员
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/28
 */

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public int save(Admin admin){
        if(admin.getId() == null){// 没有id，则表示是新增
//            admin.setPassword(MD5Utils.inputPassToFormPass(admin.getPassword()));
            admin.setPassword(SHA256Utils.SHA256Encrypt(admin.getPassword()));
            return adminMapper.insert(admin);
        }else{ //否则是更新
//            admin.setPassword(MD5Utils.inputPassToFormPass(admin.getPassword()));
            admin.setPassword(SHA256Utils.SHA256Encrypt(admin.getPassword()));
            return adminMapper.update(admin);
        }
    }

}
