package org.huangfugui.ibatis.po;

/**
 * @author zqb
 * @decription
 * @create 2017/7/7
 */
public class Label {
    int id;
    String labelName;
    int fatherId;
    int isNoun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getIsNoun() {
        return isNoun;
    }

    public void setIsNoun(int isNoun) {
        this.isNoun = isNoun;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                ", fatherId=" + fatherId +
                ", isNoun=" + isNoun +
                '}';
    }
}
