package com.campus.demo.controller;

import com.campus.demo.entity.Leave;
import com.campus.demo.mapper.LeaveMapper;
import com.campus.demo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：请假
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private LeaveService leaveService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Leave leave){
        // 新增或者更新
        return leaveService.save(leave);
    }


    // 修改
//    @PostMapping
//    public Integer updateLeave(@RequestBody Leave leave){
//        // 更新
//        return leaveMapper.updateLeave(leave);
//    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Leave> findOne(@PathVariable Integer id){
        return leaveMapper.findOne(id);
    }

    // 查询所有数据
    @GetMapping
    public List<Leave> findAll(){
        List<Leave> all = leaveMapper.findAll();
        return all;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return leaveMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return leaveMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/leave/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name, @RequestParam String leave_type, @RequestParam String status){
        pageNum = (pageNum-1)*pageSize;
        List<Leave> data = leaveMapper.selectPage(pageNum,pageSize,name,leave_type,status);
        Integer total = leaveMapper.selectTotal(name,leave_type,status);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    // 请假分页查询
    // 接口路径：/leave/leavePage?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/leavePage")
    public Map<String,Object> findLeavePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Leave> data = leaveMapper.selectLeavePage(pageNum,pageSize,username);
        Integer total = leaveMapper.selectLeaveTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
