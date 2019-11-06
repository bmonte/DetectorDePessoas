package br.ufrn.imd.modelo;

import java.util.HashMap;

public class DistanciaEuclidiana extends Knn {

	@Override
	public boolean calculate() {
		HashMap<Image, Float> imageDistanceMap = new HashMap<>(); 
		
		for (Image fromDataset : dataset.getImages()) {
			float distance = 0, diff = 0;
			//Calcula a distancia da imagem testada para cada imagem no dataset
			for (int i = 0; i < 1000; i++) {
				diff = fromDataset.getAttributes().get(i) - imageTest.get(i);
				distance = distance + (float) Math.pow(diff, 2);
			}
			imageDistanceMap.put(fromDataset, (float) Math.sqrt(distance));
		}
		
		return setLabel(labelFreq(imageDistanceMap));
	}

}
