package br.ufrn.imd.modelo;

import java.util.List;

public abstract class Knn {
	protected int k;
	protected List<Float> imageTest;
	protected Dataset dataset;
	
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public List<Float> getImageTest() {
		return imageTest;
	}

	public void setImageTest(List<Float> imageTest) {
		this.imageTest = imageTest;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public abstract boolean calculate();
	
}
