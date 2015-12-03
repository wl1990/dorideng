package com.bxs.server.model;

import java.io.Serializable;

public abstract class BasePojo<PK extends Serializable> implements Serializable{
	private static final long serialVersionUID = 1L;
	protected PK id;
	public BasePojo(){
		
	}
	public PK getId() {
		return id;
	}
	public void setId(PK id) {
		this.id = id;
	}
	
}
