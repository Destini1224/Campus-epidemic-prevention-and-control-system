package com.campus.demo.mapper;

import com.campus.demo.entity.Departure;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：离校
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Mapper
@Repository
public interface DepartureMapper {

    @Select("select * from departure order by id desc")
    List<Departure> findAll();

//    int updateDeparture(Departure departure);

    @Insert("insert into departure(username,name,plan_leave,plan_reach,destination,emergency_tel,plan_back,evidences,remarks) VALUES (#{username},#{name},#{plan_leave},#{plan_reach},#{destination},#{emergency_tel},#{plan_back},#{evidences},#{remarks})")
    int insert(Departure departure);

    int update(Departure departure);

    @Select("select * from departure where id = #{id}")
    List<Departure> findOne(@Param("id") Integer id);

    @Delete("delete from departure where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Departure> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("status") String status);

    Integer selectTotal(String name,String status);

    List<Departure> selectDeparturePage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    Integer selectDepartureTotal(String username);
}
