package org.huangfugui.ibatis.po;

/**
 * Created by huangfugui on 2017/7/31.
 */
public class LabelInput {
    private int id;
    private int userId;
    private int picId;
    private String input;

    public LabelInput(){

    }

    public LabelInput(int userId, int picId, String input) {
        this.userId = userId;
        this.picId = picId;
        this.input = input;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "LabelInput{" +
                "id=" + id +
                ", userId=" + userId +
                ", picId=" + picId +
                ", input='" + input + '\'' +
                '}';
    }
}
