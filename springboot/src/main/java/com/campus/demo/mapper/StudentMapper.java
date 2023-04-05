package com.campus.demo.mapper;

import com.campus.demo.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：学生
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2022/12/19
 */

@Mapper
@Repository
public interface StudentMapper {

    @Select("select * from student where status=0 order by id desc")
    List<Student> findAll();

    @Select("select * from student where username=#{username}")
    List<Student> findStu(String username);

    @Select("select * from student where id = #{id}")
    List<Student> findOne(@Param("id") Integer id);

    @Select("select count(*) from student where username=#{username} and password=#{password}")
    int check(Student stu);

    @Insert("insert into student(username,password,name,stuClass,dormitory,tel,email,address) VALUES (#{username},#{password},#{name},#{stuClass},#{dormitory},#{tel},#{email},#{address})")
    int insert(Student stu);

    int update(Student stu);

    int updateStu(Student stu);

    @Update("update student set status = 1 where id = #{id}")
//    @Delete("delete from student where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Student> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("stuClass") String stuClass);

    Integer selectTotal(String name, String stuClass);
}