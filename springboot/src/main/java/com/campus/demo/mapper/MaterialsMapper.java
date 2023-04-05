package com.campus.demo.mapper;

import com.campus.demo.entity.Materials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description：物资库存
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/22
 */

@Mapper
@Repository
public interface MaterialsMapper {

    @Select("select * from materials order by id desc")
    List<Materials> findAll();

    @Select("select * from materials where id = #{id}")
    List<Materials> findOne(@Param("id") Integer id);

    @Insert("insert into materials(goods_name,storage) VALUES (#{goods_name},#{storage})")
    int insert(Materials materials);

    int update(Materials materials);

    @Delete("delete from materials where id = #{id}")
    Integer deteleById(@Param("id") Integer id);

    Integer deteleBatchById(@RequestBody List<Integer> ids);

    List<Materials> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("goods_name") String goods_name);

    Integer selectTotal(String goods_name);
}
