package com.campus.demo.controller;

import com.campus.demo.config.JwtToken;
import com.campus.demo.config.Result;
import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Student;
import com.campus.demo.mapper.StudentMapper;
import com.campus.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 学生
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2022/12/19
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    //    登录
    //login方法
    @GetMapping("/login")
    public Result login(Student stu){
//        stu.setPassword(MD5Utils.inputPassToFormPass(stu.getPassword()));
        stu.setPassword(SHA256Utils.SHA256Encrypt(stu.getPassword()));
        if(studentMapper.check(stu)==1){
            //添加token
            stu.setToken(JwtToken.createToken(stu.getUsername()));
//            return admin;
            return Result.success(stu);
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
    public Integer save(@RequestBody Student stu){
        // 新增或者更新
        return studentService.save(stu);
    }

    // 修改
    @PostMapping("/username")
    public Integer updateStu(@RequestBody Student stu){
        stu.setPassword(SHA256Utils.SHA256Encrypt(stu.getPassword()));
        // 更新
        return studentMapper.updateStu(stu);
    }

    // 查询所有数据
    @GetMapping
    public List<Student> findAll(){
        List<Student> all = studentMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/username")
    public List<Student> findStu(@RequestParam String username){
        List<Student> stu = studentMapper.findStu(username);
        return stu;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Student>  findOne(@PathVariable Integer id){
        List<Student>  one = studentMapper.findOne(id);
        return one;
    }

    // 删除某条数据 假删除update student set status = 1 where id = id
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
       return studentMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return studentMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/student/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String name,@RequestParam String stuClass){
        pageNum = (pageNum-1)*pageSize;
        List<Student> data = studentMapper.selectPage(pageNum,pageSize,name,stuClass);
        Integer total = studentMapper.selectTotal(name,stuClass);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }
}
