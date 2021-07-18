package com.lohis.githubapi;

public class Repo {
	private String id;
	private String name;
	private String content;
	
	public Repo() {
		super();
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
