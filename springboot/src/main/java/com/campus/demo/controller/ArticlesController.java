package com.campus.demo.controller;

import com.campus.demo.entity.Articles;
import com.campus.demo.mapper.ArticlesMapper;
import com.campus.demo.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：文章
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Autowired
    private ArticlesService articlesService;

    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody Articles articles){
        // 新增或者更新
        return articlesService.save(articles);
    }

    // 查询所有数据
    @GetMapping
    public List<Articles> findAll(){
        List<Articles> all = articlesMapper.findAll();
        return all;
    }

    // 查询某条数据
    @GetMapping("/{id}")
    public List<Articles> findOne(@PathVariable Integer id){
        List<Articles> one = articlesMapper.findOne(id);
        return one;
    }

    // 删除某条数据
    @DeleteMapping("/{id}")
    public  Integer delete(@PathVariable Integer id){
        return articlesMapper.deteleById(id);
    }

    // 批量删除
    @PostMapping("/del/batch")
    public  Integer deleteBatch(@RequestBody List<Integer> ids){
        return articlesMapper.deteleBatchById(ids);
    }

    // 分页查询
    // 接口路径：/teacher/page?pageNum=1&pageSize=10
    // @RequestParam接收 ?pageNum=1&pageSize=10
    // limit第一个参数=(pageNum-1)*pageSize 第二个参数是pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String pub_type){
        pageNum = (pageNum-1)*pageSize;
        List<Articles> data = articlesMapper.selectPage(pageNum,pageSize,pub_type);
        Integer total = articlesMapper.selectTotal(pub_type);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }

    // 类型查询
    @GetMapping("/types")
    public Map<String,Object> findType(@RequestParam String pub_type){
        List<Articles> data = articlesMapper.selectTypes(pub_type);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        return res;

    }
}