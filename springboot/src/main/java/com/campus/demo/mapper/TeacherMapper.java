package com.campus.demo.mapper;

import com.campus.demo.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：教师
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/20
 */
@Mapper
@Repository
public interface TeacherMapper {

    @Select("select * from teacher where status==0 order by id desc")
    List<Teacher> findAll();

    @Select("select * from teacher where username=#{username}")
    List<Teacher> findTea(String username);

    @Select("select * from teacher where id = #{id}")
    List<Teacher> findOne(@Param("id") Integer id);

    @Select("select count(*) from teacher where username=#{username} and password=#{password}")
    int check(Teacher tea);

    @Insert("insert into teacher(username,password,name,tel,email,address) VALUES (#{username},#{password},#{name},#{tel},#{email},#{address})")
    int insert(Teacher tea);

    int update(Teacher tea);

    int updateTea(Teacher tea);

    @Update("update teacher set status = 1 where id = #{id}")
//    @Delete("delete from teacher where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Teacher> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String username,@Param("address") String address);

    Integer selectTotal(String name,String address);
}
