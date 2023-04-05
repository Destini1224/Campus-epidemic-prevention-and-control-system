package com.campus.demo.mapper;

import com.campus.demo.entity.Articles;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：文章管理
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@Mapper
@Repository
public interface ArticlesMapper {

    @Select("select * from articles_manage order by id DESC")
    List<Articles> findAll();

    @Select("select * from articles_manage where id = #{id}")
    List<Articles> findOne(@Param("id") Integer id);

    @Insert("insert into articles_manage(title,pub_author,pub_content,pub_type) VALUES (#{title},#{pub_author},#{pub_content},#{pub_type})")
    int insert(Articles articles);

    int update(Articles articles);

    @Delete("delete from articles_manage where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Articles> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("pub_type") String pub_type);

    Integer selectTotal(String pub_type);

    List<Articles> selectTypes(@Param("pub_type") String pub_type);
}
