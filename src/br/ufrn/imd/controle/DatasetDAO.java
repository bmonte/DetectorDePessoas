package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.Image;
import br.ufrn.imd.modelo.Dataset;

public class DatasetDAO {
	private CSV file;
	private Image img;
	private Dataset dataset;
	
	public void setFile(CSV file) {
		this.file = file;
	}
	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
	
	public void fillDataset() throws IOException {
		while(file.readInstance()) {
			img = new Image();
			
			img.setAttributes(file.getAttributes());
			img.setLabel(file.getLabel());
			
			dataset.addImages(img);
		}
	}
}
