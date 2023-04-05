package com.campus.demo.mapper;

import com.campus.demo.entity.Leave;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：请假
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Mapper
@Repository
public interface LeaveMapper {

    @Select("select * from leave_application order by id desc")
    List<Leave> findAll();

    @Insert("insert into leave_application(username,name,leave_type,start,end,reason,leave_yn,emergency_tel,remarks) VALUES (#{username},#{name},#{leave_type},#{start},#{end},#{reason},#{leave_yn},#{emergency_tel},#{remarks})")
    int insert(Leave leave);

    int update(Leave leave);
//    int updateLeave(Leave leave);

    @Select("select * from leave_application where id = #{id}")
    List<Leave> findOne(@Param("id") Integer id);

    @Delete("delete from leave_application where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Leave> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name,@Param("leave_type") String leave_type, @Param("status") String status);

    Integer selectTotal(String name,String leave_type,String status);

    List<Leave> selectLeavePage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    Integer selectLeaveTotal(String username);
}
