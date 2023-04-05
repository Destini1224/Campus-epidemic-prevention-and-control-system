package com.campus.demo.service;

import com.campus.demo.entity.Leave;
import com.campus.demo.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：请假
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/26
 */

@Service
public class LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;

    public int save(Leave leave){
        if(leave.getId() == null){// 没有id，则表示是新增
            return leaveMapper.insert(leave);
        }else{ //否则是更新
            return leaveMapper.update(leave);
        }
    }
}