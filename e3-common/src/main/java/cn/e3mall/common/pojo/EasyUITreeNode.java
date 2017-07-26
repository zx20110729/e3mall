package cn.e3mall.common.pojo;

import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * Created by ZX on 2017/7/25 15:26.
 */
public class EasyUITreeNode implements Serializable {
    private static final long serialVersionUID = -3058792697305375071L;

    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
