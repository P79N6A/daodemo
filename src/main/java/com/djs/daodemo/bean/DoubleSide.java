package com.djs.daodemo.bean;

import java.util.List;

/**
 * @author js.ding
 * @Title: DoubleSide
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/916:11
 */
public class DoubleSide {
    private List<Course> courseList;

    private StuBas stuBasList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public StuBas getStuBasList() {
        return stuBasList;
    }

    public void setStuBasList(StuBas stuBasList) {
        this.stuBasList = stuBasList;
    }

    @Override
    public String toString() {
        return "DoubleSide{" +
                "courseList=" + courseList +
                ", stuBasList=" + stuBasList +
                '}';
    }
}
