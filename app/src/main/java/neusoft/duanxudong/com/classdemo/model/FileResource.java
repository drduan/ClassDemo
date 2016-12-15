package neusoft.duanxudong.com.classdemo.model;

import java.sql.Timestamp;


public class FileResource {


    String file_subject;
    String push_name;
    private java.util.Date file_add_time;
    private String file_name;
    private int file_size;
    private String file_format;
    private String file_path;
    private long fid;

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public java.util.Date getFile_add_time() {

        return this.file_add_time;
    }

    public void setFile_add_time(Timestamp file_add_time) {
        this.file_add_time = file_add_time;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFile_format() {
        return file_format;
    }

    public void setFile_format(String file_format) {
        this.file_format = file_format;
    }


    public String getPush_name() {
        return push_name;
    }

    public void setPush_name(String push_name) {
        this.push_name = push_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

}
