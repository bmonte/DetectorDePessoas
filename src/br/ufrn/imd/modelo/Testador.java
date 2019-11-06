package br.ufrn.imd.modelo;

import java.io.IOException;
import java.util.List;

import br.ufrn.imd.controle.DatasetDAO;

public class Testador {

	public static void main(String[] args) {
		try {
			CSV arquivo = new CSV();
			
			arquivo.setPath("./data/dataset_2019_1.csv");
			
			DatasetDAO test = new DatasetDAO();
			
			test.setFile(arquivo);
			test.fillDataset();
			
			ImageHOG img = new ImageHOG();
			
			img.setPath("/home/bmonte/Downloads/1.png");
			List<Float> arrayFeature = img.extract();
			System.out.println(arrayFeature);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
