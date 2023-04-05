package com.campus.demo.controller;

import com.campus.demo.entity.Departure;
import com.campus.demo.mapper.DepartureMapper;
import com.campus.demo.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 离校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@RestController
@RequestMapping("/departure")
public class DepartureController {

    @Autowired
    private DepartureMapper departureMapper;

    @Autowired
    private DepartureService departureService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Departure departure){
        // 新增或者更新
        return departureService.save(departure);
    }

//    // 修改
//    @PostMapping
//    public Integer updateDeparture(@RequestBody Departure departure){
//        // 更新
//        return departureMapper.updateDeparture(departure);
//    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Departure> findOne(@PathVariable Integer id){
        return departureMapper.findOne(id);
    }

    // 查询所有数据
    @GetMapping
    public List<Departure> findAll(){
        List<Departure> all = departureMapper.findAll();
        return all;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return departureMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return departureMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/departure/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name, @RequestParam String status){
        pageNum = (pageNum-1)*pageSize;
        List<Departure> data = departureMapper.selectPage(pageNum,pageSize,name,status);
        Integer total = departureMapper.selectTotal(name,status);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    // 离校分页查询
    // 接口路径：/departure/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/departurePage")
    public Map<String,Object> findDeparturePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Departure> data = departureMapper.selectDeparturePage(pageNum,pageSize,username);
        Integer total = departureMapper.selectDepartureTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
