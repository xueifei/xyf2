package com.shendu.ssm.service;

import com.shendu.ssm.domain.Attendance;
import com.shendu.ssm.domain.Note;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface AttendanceService {

    /**
     * 读取excel中的数据,生成list
     */
    List<Attendance> readExcelFile( MultipartFile file);

    //将list集合数据保存到数据库
    int insertInfoBatch(List<Attendance> attendanceList);

    //根据list集合中的sId 获取班级信息
    List<Attendance> findStuClassByList(List<Attendance> attendances);

    List<Attendance> findByCreateDate(int page, int size) throws ParseException;

    boolean updateAtt(Attendance attendance);

    Attendance findById(Integer id);

    boolean deleteAtt(Integer id);

    List<Attendance> findByCreateDate();

    List<Note> MessageSend(List<Attendance> byCreateDate) throws ParseException;

    //模糊查询
    List <Attendance> fuzzyAtt(String name);

    List<Attendance> findByIds(Integer[] ids);
}
