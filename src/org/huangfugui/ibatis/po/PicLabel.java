package org.huangfugui.ibatis.po;

/**
 * @author zqb
 * @decription
 * @create 2017/7/7
 */
public class PicLabel {
    int id;
    Picture picture;
    Label label;
    float score;
    int mCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "PicLabel{" +
                "id=" + id +
                ", picture=" + picture +
                ", label=" + label +
                ", score=" + score +
                ", mCount=" + mCount +
                '}';
    }
}
