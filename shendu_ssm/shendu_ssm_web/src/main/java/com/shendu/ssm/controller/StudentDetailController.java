package com.shendu.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.shendu.ssm.domain.StudentDetail;
import com.shendu.ssm.service.StudentDetailService;
import com.shendu.ssm.utils.StudentReadExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("student")
public class StudentDetailController {
    @Autowired
    private StudentDetailService studentDetailService;

    @RequestMapping("/addStu")
    public String addStu(StudentDetail studentDetail, Model model){
        boolean isTrue = studentDetailService.addStu(studentDetail);
        model.addAttribute("mess",isTrue?"添加成功":"添加失败");
        return "redirect:findAll";
    }
    //跳转新增页面
    @RequiresPermissions("addStudent")
    @RequestMapping("/addStudent")
    public String addStudent(){

        return "addStudent";
    }
      @RequestMapping("/updateStu")
    public String updateStu(StudentDetail studentDetail, Model model){
        boolean isTrue = studentDetailService.updateStu(studentDetail);
        model.addAttribute("mess",isTrue?"修改成功":"修改失败");
        return "redirect:findAll";
    }

    //批量修改学生班级
    @RequiresPermissions("updateStuClassBatch")
    @RequestMapping(value = "/updateStuClassBatch",method = {RequestMethod.POST})
    public String updateStuClassBatch( @RequestParam("stuClass") String stuClass,@RequestParam("ids") String[] ids ){

        boolean isTrue = studentDetailService.updateStuClassBatch(stuClass,ids);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mess",isTrue?"修改成功":"修改失败");
        return "redirect:findAll";
    }
    @RequiresPermissions("deleteStu")
    @RequestMapping("/deleteStu")
    public String deleteStu(Integer id, Model model){
        boolean isTrue = studentDetailService.deleteStu(id);
        model.addAttribute("mess",isTrue?"删除成功":"删除失败");
        return "redirect:findAll";
    }
    @RequiresPermissions("stuDetail")
    @RequestMapping("/findAll")
    public String findAll(Model model,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) {
        List<StudentDetail> list = studentDetailService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo listStu=new PageInfo(list);
        model.addAttribute("listStu",listStu);
        return "listStudent";
    }

    /**
     * 更改用户，页面
     */
    @RequiresPermissions("editStu")
    @RequestMapping("editStu")
    public String edit(Model model, Integer id) {
        StudentDetail studentDetail = studentDetailService.findById(id);
        model.addAttribute("student", studentDetail);

        return "editStudent";
    }
    /**
     * 上传学生Excel表
     */
    @Transactional
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(Model model, @RequestParam(value="file",required = false) MultipartFile file) {

        List<StudentDetail> excelInfo = StudentReadExcel.getExcelInfo(file);
        for (StudentDetail studentDetail : excelInfo) {
            studentDetailService.addStu(studentDetail);
        }

        return "redirect:findAll";
    }



    /**
     * 模糊查询
     */
    @RequestMapping("fuzzyStu")
    public String fuzzyStu(Model model,String name) {
        System.out.println(name);
        List<StudentDetail> list = studentDetailService.fuzzyStu(name);
        //PageInfo就是一个分页Bean
        PageInfo listStu = new PageInfo(list);
        model.addAttribute("listStu",listStu);
        return "listStudent";
    }
}
