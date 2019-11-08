package com.shendu.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shendu.ssm.domain.Attendance;
import com.shendu.ssm.domain.Note;
import com.shendu.ssm.service.AttendanceService;
import com.shendu.ssm.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private NoteService noteService;

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        List<Attendance> ttendances = attendanceService.readExcelFile(file);
        int i = attendanceService.insertInfoBatch(ttendances);
        List<Attendance> attendances12 = attendanceService.findStuClassByList( ttendances);
        //PageInfo就是一个分页Bean
        PageInfo attendances1=new PageInfo(attendances12);
        modelAndView.addObject("attendanceList",attendances1);
        modelAndView.addObject("result",i>0?"上传成功":"上传失败");
        modelAndView.setViewName("uploadAttendance");
        return modelAndView;
    }

    //
    @RequestMapping(value="/upload1")
    public String upload1() throws UnsupportedEncodingException {

        return "upload";
    }
    //
    @RequestMapping(value="/findAll")
    public String findAll()  {

        return "attendance";
    }

    @RequestMapping(value="/findByCreateDate")
    public ModelAndView findByCreateDate(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size",
            required = true, defaultValue = "4") int size) throws ParseException {


        List<Attendance> attendances = attendanceService.findByCreateDate(page , size);
        PageInfo pageInfo = new PageInfo(attendances);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("attendanceList",pageInfo);
        modelAndView.setViewName("attendance");
        return modelAndView;
    }

    //批量发短信
    @RequestMapping(value = "/messageSend")
    public ModelAndView MessageSend() throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        List<Attendance> byCreateDate = attendanceService.findByCreateDate();
        List<Note> notes = attendanceService.MessageSend(byCreateDate);
            if(notes != null){
            int i = noteService.insertBatch(notes);
        }


        List<Note> all = noteService.findAll(1, 5);
        PageInfo info = new PageInfo(all);
        modelAndView.addObject("rs",info);
        modelAndView.setViewName("listNote");
        return modelAndView;
    }


//    @RequestMapping("/updateAtt")
//    public String updateAtt(Attendance attendance, Model model){
//        boolean isTrue = attendanceService.updateAtt(attendance);
//        model.addAttribute("mess",isTrue?"修改成功":"修改失败");
//        return "redirect:findAll";
//    }
    @RequestMapping("updateAtt")
    public String updateAtt(Model model, Integer id) {
        Attendance attendance = attendanceService.findById(id);
        model.addAttribute("attendance", attendance);

        return "editAttendance";
    }

    @RequestMapping("/updateAttendance")
    public String updateAttendance(Attendance attendance, Model model){
        boolean isTrue = attendanceService.updateAtt(attendance);
        model.addAttribute("mess",isTrue?"修改成功":"修改失败");
        return "redirect:findByCreateDate";
    }

    @RequestMapping("/deleteAtt")
    public String deleteAtt(Integer id, Model model){
        boolean isTrue = attendanceService.deleteAtt(id);
        model.addAttribute("mess",isTrue?"删除成功":"删除失败");
        return "redirect:findByCreateDate";
    }

    /**
     * 模糊查询xxxxx
     */
    @RequestMapping("fuzzyAtt")
    public String fuzzyAtt(Model model,String name) {
        //System.out.println(name);
        List<Attendance> list = attendanceService.fuzzyAtt(name);
        //PageInfo就是一个分页Bean
        PageInfo listAtt = new PageInfo(list);
        model.addAttribute("attendanceList",listAtt);
        return "attendance";
    }
}
