package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;

public class BaseNote extends BaseOpenmrsData implements java.io.Serializable {


    private int noteId;
    private String text;
    private String uuid;
    private Date dateTime;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
        this.dateTime = date;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

}
