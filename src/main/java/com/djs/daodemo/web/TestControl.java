package com.djs.daodemo.web;

import com.alibaba.fastjson.JSONObject;
import com.djs.daodemo.bean.Course;
import com.djs.daodemo.bean.CourseAndStuBas;
import com.djs.daodemo.service.TestService;
import com.djs.daodemo.utils.Post;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author js.ding
 * @Title: TestControl
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/914:37
 */
@RestController
@RequestMapping("test")
public class TestControl {
    @Autowired
    private TestService testService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("test1")
    public String getTest() {
        Boolean db = testService.getDB();
        return "string";
    }

    @RequestMapping("testParams")
    public String getParams(@RequestParam("cno") Integer cno) {
        String dBwithParams = testService.getDBwithParams(cno);
        return dBwithParams;
    }

    @RequestMapping("doubleSide")
    public List<Course> getTwoTable(@RequestParam("sno") Integer sno,@RequestParam("avgScore") Integer avgScore) {
        List<Course> list = testService.getTwoTable(sno, avgScore);
        return list;
    }

    @RequestMapping("bulkInsert")
    public int bulkInsert(@RequestParam("num") Integer num) {
        int insertNums = testService.bulkInsert(num);
        return insertNums;
    }

    @RequestMapping("bcpay")
    public String bcpayTest(@RequestParam("orderNo") String orderNo, @RequestParam("appId") String appId) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("appId", appId);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<HashMap<String, String>> objectHttpEntity = new HttpEntity<>(map,httpHeaders);
//        JSONObject jsonObject = restTemplate.postForObject("http://127.0.0.1:29003/v1/orderQuery", objectHttpEntity, JSONObject.class);
        String post = Post.post("http://127.0.0.1:29003/v1/orderQuery", map, "application/x-www-form-urlencoded");

        return JSONObject.toJSONString(post);
    }

    //根据一个list查询另一张表的一个字段自动封装到list的实体类中
    @RequestMapping("advancedPackaging")
    public String locatAdvancedPackaging(@RequestParam("sno") String sno) {

        List<CourseAndStuBas> list = testService.selectAllCourse();
        System.out.println(list);
        list = testService.selectAllStuBas(list);
//        testService.selectAvgBySno();
//        return "";
        return "";
    }

    @RequestMapping("iptest")
    public String iptest(HttpServletRequest request) throws Exception {
        /*if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
             ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("X_Real_IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;*/
        /*String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        System.out.println(XFor);
        return XFor;*/
        final String[] HEADERS_TO_TRY = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR",
                "X-Real-IP"};

        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    @RequestMapping("nginx")
    @ResponseBody
    public String testNginx() {

        return "nginx success";
    }
}




