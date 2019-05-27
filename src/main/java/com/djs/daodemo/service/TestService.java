package com.djs.daodemo.service;

import com.djs.daodemo.bean.Course;
import com.djs.daodemo.bean.CourseAndStuBas;
import com.djs.daodemo.bean.Sta;
import com.djs.daodemo.dao.CourseMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author js.ding
 * @Title: TestService
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/914:39
 */
@Service
public class TestService {
    @Autowired
    private CourseMapper courseMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Boolean getDB() {
        List<Course> courses = courseMapper.selectAll();
        logger.info("get Logger", courses);
        return true;
    }

    public String getDBwithParams(int cno) {
        List<Course> list = courseMapper.selectByCno(cno);
        return "get";
    }

    public List<Course> getTwoTable(Integer sno, Integer avgScore) {
        List<Course> list = courseMapper.selectWithTwice(sno, avgScore);
        return list;
    }

    public int bulkInsert(Integer num) {
        List<Sta> staList = new ArrayList<>();
        for (Integer i = 0; i < num; i++) {
            Sta sta = new Sta();
            sta.setString(RandomStringUtils.randomAlphanumeric(3));
            sta.setNum((int) (Math.random() * 200));
            staList.add(sta);
        }
        int i = courseMapper.insertToMuch(staList);
        return i;
    }

    public List<CourseAndStuBas> selectAllCourse() {
        List<CourseAndStuBas> courses = courseMapper.selectAllToCAndS();
        return courses;
    }

    public List<CourseAndStuBas> selectAllStuBas(List<CourseAndStuBas> list) {
        return courseMapper.selectAllStuBas(list);

    }
}

