package com.campus.demo.mapper;

import com.campus.demo.entity.Application;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：物资申请
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */
@Mapper
@Repository
public interface ApplicationMapper {

    @Select("select * from materials_application order by id desc")
    List<Application> findAll();

    @Select("select * from materials_application where id = #{id}")
    List<Application> findOne(@Param("id") Integer id);

    @Update("update materials_application set status = 1 where id = #{id}")
    int update(@Param("id") Integer id);

//    Integer updateApplication(Application application);
    @Insert("insert into materials_application(username,name,goods_name,application,remarks) VALUES (#{username},#{name},#{goods_name},#{application},#{remarks})")
    int insert(Application application);

    int updateApplication(Application application);

    @Delete("delete from materials_application where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Application> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("goods_name") String goods_name, @Param("status") String status);

    Integer selectTotal(String goods_name,String status);

    List<Application> selectApplicationPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    Integer selectApplicationTotal(String username);
}
