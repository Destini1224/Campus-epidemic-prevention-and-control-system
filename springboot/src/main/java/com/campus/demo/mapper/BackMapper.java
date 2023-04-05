package com.campus.demo.mapper;

import com.campus.demo.entity.Back;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：返校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Mapper
@Repository
public interface BackMapper {

    @Select("select * from back order by id desc")
    List<Back> findAll();

    @Insert("insert into back(username,name,plan_go,plan_reach,becity,emergency_tel,evidences,remarks) VALUES (#{username},#{name},#{plan_go},#{plan_reach},#{becity},#{emergency_tel},#{evidences},#{remarks})")
    int insert(Back back);

    int update(Back back);

//    int updateBack(Back back);

    @Select("select * from back where id = #{id}")
    List<Back> findOne(@Param("id") Integer id);

    @Delete("delete from back where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Back> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("status") String status);

    Integer selectTotal(String name,String status);

    List<Back> selectBackPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    Integer selectBackTotal(String username);
}
