package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HrMemoBase<T> extends BaseOpenmrsData implements java.io.Serializable {


    private int memoId;
    private Date dateTime;
    private String text;
    private String uuid;
    private HrMemoBase<T> parent;
    private String type;
    private Set<HrMemoBase<T>> hrMemos = new HashSet<HrMemoBase<T>>(0);


    public HrMemoBase(){

    }

    public HrMemoBase<T> getParent() {
        return parent;
    }

    public void setParent(HrMemoBase<T> parent) {
        this.parent = parent;
    }

    public Set<HrMemoBase<T>> getHrMemos() {
        return hrMemos;
    }

    public void setHrMemos(Set<HrMemoBase<T>> hrMemos) {
        this.hrMemos = hrMemos;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setText(String text) {
        this.text = text;
    }

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    @Override
    public Integer getId() {
        return memoId;
    }

    @Override
    public void setId(Integer integer) {
        memoId = integer;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
