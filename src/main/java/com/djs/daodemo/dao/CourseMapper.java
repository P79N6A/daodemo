package com.djs.daodemo.dao;

import com.djs.daodemo.bean.Course;
import com.djs.daodemo.bean.CourseAndStuBas;
import com.djs.daodemo.bean.Sta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author js.ding
 * @Title: CourseMapper
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/914:40
 */
@Mapper
public interface CourseMapper {
    List<Course> selectAll();

    List<Course> selectByCno(@Param("cno") int cno);

    List<Course> selectWithTwice(@Param("sno") Integer sno, @Param("avgScore") Integer avgScore);

    int insertToMuch(@Param("staList") List<Sta> staList);

    List<CourseAndStuBas> selectAllToCAndS();

    List<CourseAndStuBas> selectAllStuBas(@Param("lists") List<CourseAndStuBas> list);
}
