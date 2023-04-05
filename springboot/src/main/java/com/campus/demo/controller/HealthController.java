package com.campus.demo.controller;

import com.campus.demo.entity.Health;
import com.campus.demo.mapper.HealthMapper;
import com.campus.demo.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：健康信息
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/21
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthMapper healthMapper;

    @Autowired
    private HealthService healthService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Health health){
        // 新增或者更新
        return healthService.save(health);
    }

//    // 新增和修改
//    @PostMapping
//    public Integer update(@RequestBody Health health){
//        return healthMapper.update(health);
//    }

    // 查询所有数据
    @GetMapping
    public List<Health> findAll(){
        List<Health> all = healthMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Health>  findOne(@PathVariable Integer id){
        List<Health>  one = healthMapper.findOne(id);
        return one;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return healthMapper.deteleById(id);
    }

    // 批量删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return healthMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/health/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String name,@RequestParam String health, @RequestParam String u_type, @RequestParam(required = false)  String last_time){
        pageNum = (pageNum-1)*pageSize;
        List<Health> data = healthMapper.selectPage(pageNum,pageSize,name,health,u_type,last_time);
        Integer total = healthMapper.selectTotal(name,health,u_type,last_time);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    // 分页查询2
    // 接口路径：/health/page2?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/healthPage")
    public Map<String,Object> findPage2(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Health> data = healthMapper.selectHealthPage(pageNum,pageSize,username);
        Integer total = healthMapper.selectHealthTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}