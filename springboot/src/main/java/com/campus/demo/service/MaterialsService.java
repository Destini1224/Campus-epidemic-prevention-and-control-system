package com.campus.demo.service;

import com.campus.demo.entity.Materials;
import com.campus.demo.mapper.MaterialsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：物资库存
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */
@Service
public class MaterialsService {

    @Autowired
    private MaterialsMapper materialsMapper;

    public int save(Materials materials){
        if(materials.getId() == null){// 没有id，则表示是新增
            return materialsMapper.insert(materials);
        }else{ //否则是更新
            return materialsMapper.update(materials);
        }
    }
}