package com.campus.demo.controller;

import com.campus.demo.config.JwtToken;
import com.campus.demo.config.Result;
import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Teacher;
import com.campus.demo.mapper.TeacherMapper;
import com.campus.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 教师
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;

    //    登录
    //login方法
    @GetMapping("/login")
    public Result login(Teacher tea){
        tea.setPassword(SHA256Utils.SHA256Encrypt(tea.getPassword()));
        if(teacherMapper.check(tea)==1){
            //添加token
            tea.setToken(JwtToken.createToken(tea.getUsername()));
//            return admin;
            return Result.success(tea);
        }
//        return null;
        return Result.fail("用户名或密码错误");
    }
    //校验token
    @GetMapping("/checkToken")
    //接收前端请求过来的header中的token，然后去jwtoken校验方法里校验这个token
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtToken.checkToken(token);
    }

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Teacher tea){
        // 新增或者更新
        return teacherService.save(tea);
    }

    // 修改
    @PostMapping("/username")
    public Integer updateStu(@RequestBody Teacher tea){
        // 更新
        tea.setPassword(SHA256Utils.SHA256Encrypt(tea.getPassword()));
        return teacherMapper.updateTea(tea);
    }

    // 查询所有数据
    @GetMapping
    public List<Teacher> findAll(){
        List<Teacher> all = teacherMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/username")
    public List<Teacher> findTea(@RequestParam String username){
        List<Teacher> tea = teacherMapper.findTea(username);
        return tea;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Teacher>  findOne(@PathVariable Integer id){
        List<Teacher>  one = teacherMapper.findOne(id);
        return one;
    }

    // 删除某条数据 假删除update teacher set status = 1 where id = id
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return teacherMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return teacherMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/teacher/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name, @RequestParam String address){
        pageNum = (pageNum-1)*pageSize;
        List<Teacher> data = teacherMapper.selectPage(pageNum,pageSize,name,address);
        Integer total = teacherMapper.selectTotal(name,address);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }
}
