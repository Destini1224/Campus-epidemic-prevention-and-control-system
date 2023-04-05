package com.campus.demo.service;

import com.campus.demo.entity.Departure;
import com.campus.demo.mapper.DepartureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：离校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/26
 */

@Service
public class DepartureService {
    @Autowired
    private DepartureMapper departureMapper;

    public int save(Departure departure){
        if(departure.getId() == null){// 没有id，则表示是新增
            return departureMapper.insert(departure);
        }else{ //否则是更新
            return departureMapper.update(departure);
        }
    }
}