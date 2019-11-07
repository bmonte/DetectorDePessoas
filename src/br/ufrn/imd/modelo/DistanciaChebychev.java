package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DistanciaChebychev extends Knn {

	@Override
	public boolean calculate() {
		HashMap<Image, Float> imageDistanceMap = new HashMap<>(); 
		
		for (Image fromDataset : dataset.getImages()) {
			List<Float> distance = new ArrayList<Float>();
			
			//Calcula a distancia da imagem testada para cada imagem no dataset
			for (int i = 0; i < 1000; i++) {
				distance.add(imageTest.get(i) - fromDataset.getAttributes().get(i));
			}
			imageDistanceMap.put(fromDataset, Collections.max(distance));
		}
		
		return setLabel(labelFreq(imageDistanceMap));
	}

}
