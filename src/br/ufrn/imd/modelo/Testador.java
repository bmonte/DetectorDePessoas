package br.ufrn.imd.modelo;

import java.io.IOException;

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
			
			img.setPath("/home/bmonte/Downloads/positive-000353.png");
			
			Knn m = new DistanciaEuclidiana();
			
			m.setDataset(test.getDataset());
			m.setImageTest(img.extract());
			m.setK(1);
			
			if (m.calculate()) {
				System.out.println("É pessoa");
			} else {
				System.out.println("Não é pessoa");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
