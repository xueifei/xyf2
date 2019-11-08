package com.shendu.ssm.service;

import com.shendu.ssm.domain.Note;

import java.util.List;


public interface NoteService {
    boolean addNote (Note note);
    boolean updateNote (Note note);
    boolean deleteNote(int id);
    List<Note> findAll(int page, int size);
    Note findList(int id);

    int insertBatch(List<Note> notes);

    List <Note> fuzzyNote(String name);
}
