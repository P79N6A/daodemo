package com.djs.daodemo.bean;

/**
 * @author js.ding
 * @Title: StuBas
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/916:07
 */
public class StuBas {
    private Integer sno;

    private Integer averageScore;

    private Integer failedSubjectsNums;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getFailedSubjectsNums() {
        return failedSubjectsNums;
    }

    public void setFailedSubjectsNums(Integer failedSubjectsNums) {
        this.failedSubjectsNums = failedSubjectsNums;
    }

    @Override
    public String toString() {
        return "StuBas{" +
                "sno=" + sno +
                ", averageScore=" + averageScore +
                ", failedSubjectsNums=" + failedSubjectsNums +
                '}';
    }
}
