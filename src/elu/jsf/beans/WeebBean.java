package elu.jsf.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import elu.db.WeebModel;
import elu.db.WeebQueries;

@ManagedBean(name="weeb")
public class WeebBean {
	private String comment;
	private List<WeebModel> weebs;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<WeebModel> getWeebs() {
		if (weebs == null) weebs = WeebQueries.getAll();
		return weebs;
	}

	public void setWeebs(List<WeebModel> weebs) {
		this.weebs = weebs;
	}

	public void send() {
		if (comment != null) System.out.println(comment);
	}
}
