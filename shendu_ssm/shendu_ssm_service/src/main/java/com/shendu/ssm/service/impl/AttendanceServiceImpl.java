package com.shendu.ssm.service.impl;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.shendu.ssm.domain.Attendance;

import com.shendu.ssm.domain.Note;
import com.shendu.ssm.domain.StudentDetail;
import com.shendu.ssm.mapper.AttendanceDao;
import com.shendu.ssm.mapper.StudentDetailDao;
import com.shendu.ssm.service.AttendanceService;
import com.shendu.ssm.utils.DateUtils;
import com.shendu.ssm.utils.MessageXsendUtils;
import com.shendu.ssm.utils.ReadExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private StudentDetailDao studentDetailDao;

    @Override
    public List<Attendance> readExcelFile(MultipartFile file) {
        String result ="";  
        //创建处理EXCEL的类  
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取上传的事件单  
        List<Attendance> attendanceList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
        //和你具体业务有关,这里不做具体的示范  

        return attendanceList;
    }

    @Override
    public int insertInfoBatch(List<Attendance> attendanceList) {
        int i = 0;
        if(attendanceList != null && !attendanceList.isEmpty()){
            i = attendanceDao.insertInfoBatch(attendanceList);

        }
        return i;
    }



    @Override
    public List<Attendance> findByCreateDate(int page, int size) throws ParseException {


        PageHelper.startPage(page,size);
        return attendanceDao.findByCreateDate(findDate());
    }

    @Override
    public List<Attendance> findByCreateDate() {
        List<Attendance> byCreateDate = attendanceDao.findByCreateDate(findDate());
        return byCreateDate;
    }

    //发送短信
    @Override
    public List<Note> MessageSend(List<Attendance> byCreateDate) throws ParseException {
        List<Note> noteList = new ArrayList<>();
        for (Attendance attendance : byCreateDate) {

            List<StudentDetail> byNames = studentDetailDao.findByName(attendance.getName());
            if (byNames.size() == 1){
                attendance.setStudent(byNames.get(0));
            }else {
                throw new  RuntimeException("学生姓名重复");
            }

            //迟到短信
            if (attendance.getStatus() == 1){
                Note note = new Note();
                String mess = MessageXsendUtils.getmessige1(attendance.getStudent().getPhone(),null);
                Map map = (Map) JSON.parse(mess);
                String value = (String) map.get("status");
                if (value.equals("error")){
                    note.setStatus(1);
                } else if (value.equals("success")){
                    note.setStatus(0);
                }
                note.setName(attendance.getName());
                note.setPhone(attendance.getStudent().getPhone());
                note.setTemplateId(1);
                noteList.add(note);
            }
            //旷课短信
            if (attendance.getStatus() == 2){
                //给学生发
                Note note = new Note();
                Map m = new HashMap();
                m.put("current_date",DateUtils.date2String(new Date(),"yyyy-MM-dd"));
                m.put("current_time",DateUtils.date2String(new Date(),"HH:mm"));
                String code = JSONUtils.toJSONString(m);
                String mess = MessageXsendUtils.getmessige2(attendance.getStudent().getPhone(),code);
                Map map = (Map) JSON.parse(mess);
                String value = (String) map.get("status");
                if (value.equals("error")){
                    note.setStatus(1);
                } else if (value.equals("success")){
                    note.setStatus(0);
                }
                note.setName(attendance.getName());
                note.setPhone(attendance.getStudent().getPhone());
                note.setTemplateId(2);
                noteList.add(note);
                //给家长发
                Map m2 = new HashMap();
                m2.put("current_time",DateUtils.date2String(new Date(),"HH:mm"));
                m2.put("student_name",attendance.getName());
                String code1 = JSONUtils.toJSONString(m2);
                Note note1 = new Note();
                String mess1 = MessageXsendUtils.getmessige3(attendance.getStudent().getParentPhone(),code1);
                Map map1 = (Map) JSON.parse(mess1);
                String value1 = (String) map1.get("status");
                if (value1.equals("error")){
                    note1.setStatus(1);
                } else if (value1.equals("success")){
                    note1.setStatus(0);
                }
                note1.setName(attendance.getName());
                note1.setPhone(attendance.getStudent().getParentPhone());
                note1.setTemplateId(3);
                noteList.add(note1);
            }
        }

        return noteList;
    }

    @Override
    public List<Attendance> findByIds(Integer[] ids) {
        return attendanceDao.findByIds(ids);
    }

    //获取当天凌晨时间
    public Date findDate() {
        String date2String = DateUtils.date2String(new Date(), "yyyy-MM-dd");
        String str = " 00:00:00";
        String  dateStr = date2String.concat(str);
        Date date = null;
        try {
            date = DateUtils.string2Date(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    @Override
    public boolean updateAtt(Attendance attendance) {
        try{
            attendanceDao.updateAtt(attendance);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Attendance findById(Integer id) {
        Attendance attendance = attendanceDao.findById(id);
        return attendance;
    }

    @Override
    public boolean deleteAtt(Integer id) {

        try {
            attendanceDao.deleteAtt(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Attendance> fuzzyAtt(String name) {

        return attendanceDao.fuzzyAtt(name);
    }
}
