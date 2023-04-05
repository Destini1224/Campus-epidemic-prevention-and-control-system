package com.campus.demo.controller;

import com.campus.demo.config.JwtToken;
import com.campus.demo.config.Result;
import com.campus.demo.config.SHA256Utils;
import com.campus.demo.entity.Admin;
import com.campus.demo.mapper.AdminMapper;
import com.campus.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 管理员
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

//    登录
    //login方法
    @GetMapping("/login")
    public Result login(Admin admin){
//        admin.setPassword(MD5Utils.inputPassToFormPass(admin.getPassword()));
        admin.setPassword(SHA256Utils.SHA256Encrypt(admin.getPassword()));
        if(adminMapper.check(admin)==1){
            //添加token
            admin.setToken(JwtToken.createToken(admin.getUsername()));
//            return admin;
            return Result.success(admin);
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
    public Integer save(@RequestBody Admin admin){
        // 新增或者更新
        return adminService.save(admin);
    }

//     修改密码
    @PostMapping("/username")
    public Integer updateAdmin(@RequestParam String username,@RequestParam String password){
        // 新增或者更新
//        String password2 = MD5Utils.inputPassToFormPass(password);
        String password2= SHA256Utils.SHA256Encrypt(password);
        return adminMapper.updateAdmin(username,password2);
    }

    // 查询所有数据
    @GetMapping
    public List<Admin> findAll(){
        List<Admin> all = adminMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Admin>  findOne(@PathVariable Integer id){
        List<Admin>  one = adminMapper.findOne(id);
        return one;
    }

    // 删除某条数据 假删除update admin set status = 1 where id = id
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return adminMapper.deteleById(id);
    }

    // 批量删除 假删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return adminMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/teacher/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        pageNum = (pageNum-1)*pageSize;
        List<Admin> data = adminMapper.selectPage(pageNum,pageSize,username);
        Integer total = adminMapper.selectTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}