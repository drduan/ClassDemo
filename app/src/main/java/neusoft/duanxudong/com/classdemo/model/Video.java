package neusoft.duanxudong.com.classdemo.model;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String vname;
	private String img_url;
	private String video_url;
	private String introduce;


	private subject subject;

	private Date add_date;

	public Date getAdd_date() {
		return add_date;

	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

}
