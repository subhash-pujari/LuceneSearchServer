package com.evoapps.datastructure;

public class PubSim {

	private String pub1;
	private String pub2;
	private float sim;
	
	public PubSim(String pub1, String pub2, float sim) {
		// TODO Auto-generated constructor stub
		
		this.setPub1(pub1);
		this.setPub2(pub2);
		this.setSim(sim);
	}

	public String getPub1() {
		return pub1;
	}

	public void setPub1(String pub1) {
		this.pub1 = pub1;
	}

	public String getPub2() {
		return pub2;
	}

	public void setPub2(String pub2) {
		this.pub2 = pub2;
	}

	public float getSim() {
		return sim;
	}

	public void setSim(float sim) {
		this.sim = sim;
	}
}
