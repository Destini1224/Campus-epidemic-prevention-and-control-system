package com.campus.demo.mapper;

import com.campus.demo.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：管理员
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */

@Mapper
@Repository
public interface AdminMapper {

    @Select("select * from admin where status=0 order by id desc")
    List<Admin> findAll();

    @Select("select * from admin where id = #{id}")
    List<Admin> findOne(@Param("id") Integer id);

    @Select("select count(*) from admin where username=#{username} and password=#{password}")
    Integer check(Admin admin);

    @Insert("insert into admin(username,password) VALUES (#{username},#{password})")
    Integer insert(Admin admin);

    int update(Admin admin);

    Integer updateAdmin(String username,String password);

    @Update("update admin set status = 1 where id = #{id}")
//    @Delete("delete from admin where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Admin> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("username") String username);

    Integer selectTotal(String username);
}

