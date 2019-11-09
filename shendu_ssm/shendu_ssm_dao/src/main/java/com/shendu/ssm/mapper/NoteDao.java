package com.shendu.ssm.mapper;

import com.shendu.ssm.domain.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteDao {

    List<Note> findAll();

    void addNote(Note note);

    void updateNote(Note note);

    void deleteNote(int id);

    Note findList(int id);

    int insertBatch(List<Note> notes);

    List <Note> fuzzyNote(@Param("name") String name);
}
