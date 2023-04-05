package com.campus.demo.controller;

import com.campus.demo.entity.Materials;
import com.campus.demo.mapper.MaterialsMapper;
import com.campus.demo.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：物资信息
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */
@RestController
@RequestMapping("/materials")
public class MaterialsController {

    @Autowired
    private MaterialsMapper materialsMapper;

    @Autowired
    private MaterialsService materialsService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Materials materials){
        // 新增或者更新
        return materialsService.save(materials);
    }

//    // 新增
//    @PostMapping
//    public Integer insert(@RequestBody Materials materials){
//        // 新增或者更新
//        return materialsMapper.insert(materials);
//    }

    // 查询所有数据
    @GetMapping
    public List<Materials> findAll(){
        List<Materials> all = materialsMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Materials>  findOne(@PathVariable Integer id){
        List<Materials>  one = materialsMapper.findOne(id);
        return one;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return materialsMapper.deteleById(id);
    }

    // 批量删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return materialsMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/materials/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String goods_name){
        pageNum = (pageNum-1)*pageSize;
        List<Materials> data = materialsMapper.selectPage(pageNum,pageSize,goods_name);
        Integer total = materialsMapper.selectTotal(goods_name);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}