package com.shendu.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.shendu.ssm.domain.Note;
import com.shendu.ssm.service.NoteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @RequiresPermissions("noteDetail")
    @RequestMapping("findAll")
    public String findAll(Model model, @RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size){
        List<Note> list = noteService.findAll(page, size);
        PageInfo rs = new PageInfo(list);
        model.addAttribute("rs",rs);
        return "listNote";
    }
    @RequiresPermissions("deleteNote")
    @RequestMapping("deleteNote")
    public String deleteNote(Integer id, Model model){
        boolean isTrue = noteService.deleteNote(id);
        model.addAttribute("mess",isTrue?"删除成功":"删除失败");
        return "redirect:findAll";
}
}
