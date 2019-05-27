package com.djs.daodemo.bean;

/**
 * @author js.ding
 * @Title: Sta
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/917:47
 */
public class Sta {
    private int num;

    private String string;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "Sta{" +
                "num=" + num +
                ", string='" + string + '\'' +
                '}';
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
