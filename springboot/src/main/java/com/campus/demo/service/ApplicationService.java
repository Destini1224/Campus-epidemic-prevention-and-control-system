package com.campus.demo.service;

import com.campus.demo.entity.Application;
import com.campus.demo.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：物资申请
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/26
 */

@Service
public class ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    public int save(Application application){
        if(application.getId() == null){// 没有id，则表示是新增
            return applicationMapper.insert(application);
        }else{ //否则是更新
            return applicationMapper.updateApplication(application);
        }
    }
}