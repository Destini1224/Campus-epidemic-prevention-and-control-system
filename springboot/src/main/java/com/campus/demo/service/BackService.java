package com.campus.demo.service;

import com.campus.demo.entity.Back;
import com.campus.demo.mapper.BackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：返校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/26
 */

@Service
public class BackService {
    @Autowired
    private BackMapper backMapper;

    public int save(Back back){
        if(back.getId() == null){// 没有id，则表示是新增
            return backMapper.insert(back);
        }else{ //否则是更新
            return backMapper.update(back);
        }
    }
}