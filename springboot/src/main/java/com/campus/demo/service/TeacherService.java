package com.campus.demo.service;

import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Teacher;
import com.campus.demo.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：老师
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public int save(Teacher tea){
        if(tea.getId() == null){// 没有id，则表示是新增
            tea.setPassword(SHA256Utils.SHA256Encrypt(tea.getPassword()));
            return teacherMapper.insert(tea);
        }else{ //否则是更新
            tea.setPassword(SHA256Utils.SHA256Encrypt(tea.getPassword()));
            return teacherMapper.update(tea);
        }
    }
}
