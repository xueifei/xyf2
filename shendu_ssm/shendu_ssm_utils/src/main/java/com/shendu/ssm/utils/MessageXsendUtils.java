package com.shendu.ssm.utils;

import java.io.*;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class MessageXsendUtils {
    public static String your_appid;//appid 必需 在SUBMAIL应用集成中创建的短信应用ID
    public static String your_appkey;//密码
    public static String project_id1;//项目标记的ID
    public static String project_id2;//项目标记的ID
    public static String project_id3;//项目标记的ID
    static {

        try {
            InputStream in = MessageXsendUtils.class.getClassLoader().getResourceAsStream("const.properties");
            Properties prop = new Properties();
            prop.load(in);
            your_appid = prop.getProperty("messiage_appid");
            your_appkey = (String) prop.get("messiage_appkey");
            project_id1 = (String) prop.get("messiage_project_id1");
            project_id2 = (String) prop.get("messiage_project_id2");
            project_id3 = (String) prop.get("messiage_project_id3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String xsend(String appid, String appkey, String to, String project, String vars) {
        String URL = "http://api.mysubmail.com/message/xsend.json";
        HashMap<String, String> paramer = new HashMap<String, String>();
        paramer.put("appid", appid);//创建的信息应用ID
        paramer.put("signature", appkey);//创建信息时的密码
        paramer.put("to", to);//收件人的手机号，现在短信仅支持一对一模式（该参数现在仅能提交一个联系人）
        paramer.put("project", project);//项目标记（ID）
        if (vars !="" && vars != null)
        paramer.put("vars", vars);//使用文本变量动态控制短信中的文本
        return executePostByUsual(URL, paramer);

    }


    public static String executePostByUsual(String actionURL, HashMap<String, String> parameters) {
        String response = "";
        try {
            URL url = new URL(actionURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置请求数据内容
            String requestContent = "";
            Set<String> keys = parameters.keySet();
            for (String key : keys) {
                requestContent = requestContent + key + "=" + URLEncoder.encode(parameters.get(key), "UTF-8") + "&";
            }
            requestContent = requestContent.substring(0, requestContent.lastIndexOf("&"));
            DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
            // 使用write(requestContent.getBytes())是为了防止中文出现乱码
            ds.write(requestContent.getBytes());
            ds.flush();
            try {
                // 获取URL的响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String s = "";
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    s += temp;
                }
                response = s;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No response get!!!");
            }
            ds.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Request failed!");
        }
        return response;
    }

    public static void main(String[] args) {
        // xsend demo

//
//         String response = MessageXsendDemo.xsend("your_appid", "your_appkey",
//          "telphone_number", "project_id", "var变量（jsonString）");
//
//        // "{\"code\":\"123456\",\"time\":\"10\"}");
//         System.out.println(response);
        // ProxyTeacherWatchServiceImpl impl = new
        // ProxyTeacherWatchServiceImpl();
        // Integer getrandom = impl.getrandom();
        // getmessige("15034358109", 888888);
        //getmessige("13303475875", 888888);
       //getmessige("18600571419", "111");
        String code = "{\"current_date\":\"2019-11-08\",\"current_time\":\"10:51\"}";
        getmessige3("18600571419", code);

    }
    //迟到短信
    public static String  getmessige1(String tel, String code) {
        // String response = MessageXsendDemo.xsend("29469",
        // "78452dce814b760875873e45ad07bb8c", tel, "`",
        // "{\"code\":\""+code+"\",\"time\":\"10\"}");
        String response = MessageXsendUtils.xsend(your_appid, your_appkey, tel, project_id1,
                  code );

        return response;

    }
    //学院旷课短信
    public static String  getmessige2(String tel, String code) {

        String response = MessageXsendUtils.xsend(your_appid, your_appkey, tel, project_id2,
                code );

        return response;

    }
    //旷课短信
    public static String  getmessige3(String tel, String code) {

        String response = MessageXsendUtils.xsend(your_appid, your_appkey, tel, project_id3,
                code );

        return response;

    }


}