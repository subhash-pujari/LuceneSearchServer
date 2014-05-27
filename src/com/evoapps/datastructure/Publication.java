package com.evoapps.datastructure;

public class Publication {

	private String title;
	private String id;
	
	public Publication(String title, String id){
		this.setTitle(title);
		this.setId(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
