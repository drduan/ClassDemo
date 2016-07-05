package neusoft.duanxudong.com.classdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;



public class subject {


	private long id;


	@SerializedName(value = "Subject" ,alternate = {"subject"})
	private String Subject;

	private int flag;


	public Set<Video> sub_v;


	public Set<Video> getSub_v() {
		return sub_v;
	}

	public void setSub_v(Set<Video> sub_v) {

		this.sub_v = sub_v;
	}


	private String sub_img;

	public String getSub_teacher() {
		return sub_teacher;
	}

	public void setSub_teacher(String sub_teacher) {
		this.sub_teacher = sub_teacher;
	}


	private String sub_teacher;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getSub_img() {
		return sub_img;
	}

	public void setSub_img(String sub_img) {
		this.sub_img = sub_img;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		this.Subject = subject;
	}

}
