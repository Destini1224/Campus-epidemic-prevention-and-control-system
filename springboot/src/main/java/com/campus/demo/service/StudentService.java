package com.campus.demo.service;

import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Student;
import com.campus.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：学生
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2022/12/19
 */

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public int save(Student stu){
        if(stu.getId() == null){// 没有id，则表示是新增
            stu.setPassword(SHA256Utils.SHA256Encrypt(stu.getPassword()));
           return studentMapper.insert(stu);
        }else{ //否则是更新
            stu.setPassword(SHA256Utils.SHA256Encrypt(stu.getPassword()));
           return studentMapper.update(stu);
        }
    }

}
