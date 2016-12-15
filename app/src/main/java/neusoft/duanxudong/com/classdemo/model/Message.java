package neusoft.duanxudong.com.classdemo.model;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 5306636476704179734L;
    private Integer msg_id;
    private Integer msg_kind;
    private String msg_title;
    private String msg_img;

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public Integer getMsg_kind() {
        return msg_kind;
    }

    public void setMsg_kind(Integer msg_kind) {
        this.msg_kind = msg_kind;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public String getMsg_img() {
        return msg_img;
    }

    public void setMsg_img(String msg_img) {
        this.msg_img = msg_img;
    }

}
