package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Image {
	
	private ArrayList<Float> attributes;
	private String label;
	
	public ArrayList<Float> getAttributes() {
		return attributes;
	}
	public void setAttributes(ArrayList<Float> attributes) {
		this.attributes = attributes;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
