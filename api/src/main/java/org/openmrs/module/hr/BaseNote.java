package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseNote extends BaseOpenmrsData implements java.io.Serializable {

    private String text;
    private String uuid;
    private Integer priority;
    private String noteType;



    public BaseNote(){

    }

    public BaseNote(String text, String uuid) {
        this.text = text;
        this.uuid = uuid;
    }

    public BaseNote(String noteType, String text, Integer priority, String uuid) {
        this.text = text;
        this.uuid = uuid;
        this.priority = priority;
        this.noteType = noteType;

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
