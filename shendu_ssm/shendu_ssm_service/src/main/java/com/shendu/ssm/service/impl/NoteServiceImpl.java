package com.shendu.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.shendu.ssm.domain.Note;
import com.shendu.ssm.mapper.NoteDao;
import com.shendu.ssm.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;

    @Override
    public boolean addNote(Note note) {
        try{
            noteDao.addNote(note);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateNote(Note note) {
        try{
            noteDao.updateNote(note);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNote(int id) {
        try{
            noteDao.deleteNote(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Note> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Note> list = noteDao.findAll();
        return list;
    }

    @Override
    public Note findList(int id) {
        Note note = noteDao.findList(id);
        return note;
    }

    @Override
    public int insertBatch(List<Note> notes) {
        return noteDao.insertBatch(notes);
    }

    @Override
    public List<Note> fuzzyNote(String name) {
        return noteDao.fuzzyNote(name);
    }
}
