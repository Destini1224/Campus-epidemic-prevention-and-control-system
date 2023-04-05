package com.campus.demo.service;

import com.campus.demo.entity.Articles;
import com.campus.demo.mapper.ArticlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：文章管理
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@Service
public class ArticlesService {

    @Autowired
    private ArticlesMapper articlesMapper;

    public int save(Articles articles){
        if(articles.getId() == null){// 没有id，则表示是新增
            return articlesMapper.insert(articles);
        }else{ //否则是更新
            return articlesMapper.update(articles);
        }
    }
}
