package com.campus.demo.controller;

import com.campus.demo.entity.Back;
import com.campus.demo.mapper.BackMapper;
import com.campus.demo.service.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 返校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@RestController
@RequestMapping("/back")
public class BackController {

    @Autowired
    private BackMapper backMapper;

    @Autowired
    private BackService backService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Back back){
        // 新增或者更新
        return backService.save(back);
    }


//    // 修改
//    @PostMapping
//    public Integer updateBack(@RequestBody Back back){
//        // 更新
//        return backMapper.updateBack(back);
//    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Back> findOne(@PathVariable Integer id){
        return backMapper.findOne(id);
    }

    // 查询所有数据
    @GetMapping
    public List<Back> findAll(){
        List<Back> all = backMapper.findAll();
        return all;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return backMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return backMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/back/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name, @RequestParam String status){
        pageNum = (pageNum-1)*pageSize;
        List<Back> data = backMapper.selectPage(pageNum,pageSize,name,status);
        Integer total = backMapper.selectTotal(name,status);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }

    // 返校分页查询
    // 接口路径：/back/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/backPage")
    public Map<String,Object> findDeparturePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Back> data = backMapper.selectBackPage(pageNum,pageSize,username);
        Integer total = backMapper.selectBackTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
