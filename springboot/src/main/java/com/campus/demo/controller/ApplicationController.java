package com.campus.demo.controller;

import com.campus.demo.entity.Application;
import com.campus.demo.mapper.ApplicationMapper;
import com.campus.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：物资申请
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationService applicationService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Application application){
        // 新增或者更新
        return applicationService.save(application);
    }


    // 修改
    @PostMapping("/{id}")
    public Integer update(@PathVariable Integer id){
        // 新增或者更新
        return applicationMapper.update(id);
    }


//    // 修改
//    @PostMapping
//    public Integer updateApplication(@RequestBody Application application){
//        // 新增或者更新
//        return applicationMapper.updateApplication(application);
//    }

    // 查询所有数据
    @GetMapping
    public List<Application> findAll(){
        List<Application> all = applicationMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Application>  findOne(@PathVariable Integer id){
        List<Application>  one = applicationMapper.findOne(id);
        return one;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return applicationMapper.deteleById(id);
    }

    // 批量删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return applicationMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/application/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String goods_name, @RequestParam(required = false) String status){
        pageNum = (pageNum-1)*pageSize;
        List<Application> data = applicationMapper.selectPage(pageNum,pageSize,goods_name,status);
        Integer total = applicationMapper.selectTotal(goods_name,status);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    // 分页查询
    // 接口路径：/application/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/applicationPage")
    public Map<String,Object> findDeparturePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Application> data = applicationMapper.selectApplicationPage(pageNum,pageSize,username);
        Integer total = applicationMapper.selectApplicationTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}