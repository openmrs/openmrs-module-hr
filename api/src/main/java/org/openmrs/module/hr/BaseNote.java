package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;

import java.util.HashSet;
import java.util.Set;

public class BaseNote extends BaseOpenmrsData implements java.io.Serializable {


    private int noteId;
    private String text;
    private String uuid;
    private Integer priority;
    private String noteType;


    public BaseNote(){

    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getId() {
        return noteId;
    }

    @Override
    public void setId(Integer integer) {
        noteId = integer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

}
