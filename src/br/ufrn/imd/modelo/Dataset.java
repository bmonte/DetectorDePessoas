package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Dataset {
	private ArrayList<Image> images;
	
	public Dataset() {
		images = new ArrayList<Image>();
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void addImages(Image img) {
		images.add(img);
	}
	
	public int size() {
		return images.size();
	}
	
}
