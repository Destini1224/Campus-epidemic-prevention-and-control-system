package com.campus.demo.service;

import com.campus.demo.entity.Health;
import com.campus.demo.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：健康信息
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/26
 */

@Service
public class HealthService {
    @Autowired
    private HealthMapper healthMapper;

    public int save(Health health){
        if(health.getId() == null){// 没有id，则表示是新增
            return healthMapper.insert(health);
        }else{ //否则是更新
            return healthMapper.update(health);
        }
    }

}
