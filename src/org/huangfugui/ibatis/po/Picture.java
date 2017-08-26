package org.huangfugui.ibatis.po;

/**
 * @author zqb
 * @decription
 * @create 2017/7/7
 */
public class Picture {
    int id;
    String path;
    int mCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", mCount=" + mCount +
                '}';
    }
}
