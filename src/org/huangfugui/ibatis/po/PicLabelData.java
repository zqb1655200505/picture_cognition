package org.huangfugui.ibatis.po;

import java.util.List;

/**
 * Created by huangfugui on 2017/7/9.
 * 适应前端格式POJO
 */
public class PicLabelData {
    int picId;
    String picPath;
    List<Label> labels;
    List<Float> scores;

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Float> getScores() {
        return scores;
    }

    public void setScores(List<Float> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "PicLabelData{" +
                "picId=" + picId +
                ", picPath='" + picPath + '\'' +
                ", labels=" + labels +
                ", scores=" + scores +
                '}';
    }
}
