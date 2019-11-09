package com.shendu.ssm.mapper;

import com.shendu.ssm.domain.StudentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDetailDao {
    public void addStu(StudentDetail studentDetail);
    public void updateStu(StudentDetail studentDetail);
    public void deleteStu(Integer id);
    public List<StudentDetail> findAll();
    public StudentDetail findById(Integer id);

    void updateStuClassBatch(@Param("stuClass") String stuClass,@Param("list") Integer[] ids);

    List<StudentDetail> findByName(String name);
}
