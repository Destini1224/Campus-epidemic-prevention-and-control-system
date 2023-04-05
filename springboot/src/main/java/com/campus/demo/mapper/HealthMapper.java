package com.campus.demo.mapper;

import com.campus.demo.entity.Health;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：健康信息
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/21
 */
@Mapper
@Repository
public interface HealthMapper {

    @Select("select * from health_manage order by last_time DESC")
    List<Health> findAll();

    @Select("select * from health_manage where id = #{id} order by last_time DESC")
    List<Health> findOne(@Param("id") Integer id);

    @Insert("insert into health_manage(username,name,temperature,address,health,contact_infection,remarks,u_type) VALUES (#{username},#{name},#{temperature},#{address},#{health},#{contact_infection},#{remarks},#{u_type})")
    int insert(Health health);

    int update(Health health);

    @Delete("delete from health_manage where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Health> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("health") String health, @Param("u_type") String u_type,@Param("last_time") String last_time);

    Integer selectTotal(String name,String health,String u_type,String last_time);

    List<Health> selectHealthPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    Integer selectHealthTotal(String username);
}
