package br.ufrn.imd.modelo;

import java.io.IOException;

import br.ufrn.imd.controle.DatasetDAO;

public class Testador {

	public static void main(String[] args) {
		CSV arquivo = new CSV();
		Dataset dataset = new Dataset();
		
		arquivo.setPath("/home/bmonte/Downloads/dataset_2019_1.csv");
		
		DatasetDAO test = new DatasetDAO();
		
		test.setFile(arquivo);
		test.setDataset(dataset);
		
		try {
			test.fillDataset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(dataset.getImages().get(i).getAttributes());
			//System.out.println(dataset.getImages().get(i).getLabel());
		}
	}

}
