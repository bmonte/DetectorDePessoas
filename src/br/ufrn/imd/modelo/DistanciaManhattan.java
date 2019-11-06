package br.ufrn.imd.modelo;

import java.util.HashMap;

public class DistanciaManhattan extends Knn{

	@Override
	public boolean calculate() {
		HashMap<Image, Float> imageDistanceMap = new HashMap<>(); 
		
		for (Image fromDataset : dataset.getImages()) {
			float distance = 0;
			//Calcula a distancia da imagem testada para cada imagem no dataset
			for (int i = 0; i < 1000; i++) {
				distance = distance + Math.abs(fromDataset.getAttributes().get(i) - imageTest.get(i));
			}
			imageDistanceMap.put(fromDataset, distance);
		}
		
		return setLabel(labelFreq(imageDistanceMap));
	}
}
