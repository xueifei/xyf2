package com.shendu.ssm.mapper;


import com.shendu.ssm.domain.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceDao {
    int insertInfoBatch(List<Attendance> attendanceList);

    List<Attendance> findByCreateDate(@Param("date") Date dateStr);

    void updateAtt(Attendance attendance);

    public Attendance findById(Integer id);

    public void deleteAtt(Integer id);

    List<Attendance> findByIds(@Param("list")Integer[] ids);
}
