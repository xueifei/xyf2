package com.shendu.ssm.domain;


import lombok.Data;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

@Data
@ToString
public class Attendance {

    private String stuClass;
    private Long id;
    private String name;
    private Date attendanceDate; //打卡时间
    private Integer status; //考勤状态 0 正常 1 迟到 2 旷课
    private String statusStr;
    private Date createDate;//规定时间
    private Integer sId;//学生编号，唯一标识，对应学生详情表id
    private StudentDetail student;

    public Date getCreateDate() {
        return new Date();
    }

    public String getStatusStr() {
        if (status != null) {
            if (status == 0) {
                statusStr = "正常";
            } else if (status == 1) {
                statusStr = "迟到";
            } else if (status == 2) {
                statusStr = "旷课";
            } else if (status == 3) {
                statusStr = "请假";
            }
        }

        return statusStr;
    }

    public Integer getStatus() {
        if (status == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (attendanceDate != null && simpleDateFormat.format(attendanceDate).equals(simpleDateFormat.format(new Date()))) {
                Date now = null;
                Date begin = null;
                Date end = null;
                Date end00 = null;
                Date begin1 = null;
                Date end1 = null;
                Date begin2 = null;
                Date end2 = null;
                Date end22 = null;
                Date begin3 = null;
                Date end3 = null;
                Date begin4 = null;
                Date end4 = null;
                Date end44 = null;
                Date begin5 = null;
                Date end5 = null;

                SimpleDateFormat df = new SimpleDateFormat("HH:mm");


                try {
                    now = df.parse(df.format(attendanceDate));


                    begin = df.parse("08:00");

                    end = df.parse("09:00");
                    end00 = df.parse("09:01");

                    begin1 = df.parse("09:30");

                    end1 = df.parse("11:50");

                    begin2 = df.parse("13:00");

                    end2 = df.parse("14:00");
                    end22 = df.parse("14:01");
                    begin3 = df.parse("14:30");

                    end3 = df.parse("17:50");

                    begin4 = df.parse("18:10");

                    end4 = df.parse("19:00");
                    end44 = df.parse("19:01");
                    begin5 = df.parse("19:30");

                    end5 = df.parse("21:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (now.before(end00) && now.after(begin)
                        || now.before(end22) && now.after(begin2)
                        || now.before(end44) && now.after(begin4)) {

                    return 0;

                } else {
                    if (now.before(begin1) && now.after(end) || now.before(begin3) && now.after(end2)
                            || now.before(begin5) && now.after(end4)) {

                        return 1;

                    } else {
                        if (now.before(end1) && now.after(begin1) || now.before(end3) && now.after(begin3)
                                || now.before(end5) && now.after(begin5)) {

                            return 2;
                        } else if (df.format(now).equals("09:30") || df.format(now).equals("14:30") || df.format(now).equals("19:30")) {
                            return 2;
                        }
                    }
                }
            }
        }
        return status;
    }


}
