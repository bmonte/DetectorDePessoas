package br.ufrn.imd.modelo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Knn implements TypeVerify{
	protected int k;
	protected List<Float> imageTest;
	protected Dataset dataset;
	protected Image imageResult;
	
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
	
	public int[] labelFreq(HashMap<Image, Float> imageDistanceMap) {
		//Ordena o mapa
		imageDistanceMap = sortByValue(imageDistanceMap);
		
		int index = 0;
		int[] result = new int[2];
		
		for (Image key : imageDistanceMap.keySet()) {
		    if (index++ < k) {
		        if (isSomething(key.getLabel())) {
					result[0]++;
				} else {
					result[1]++;
				}
		    } else {
		    	break;
		    }
		}
		return result;
		
	}
	
	public boolean setLabel(int[] result) {
		//Pos[0] é qntPessoas, Pos[1] é qntNaoPessoa
		if (result[0] >= result[1]) {
			return true;
		} 
		
		return false;
	}
	
	//Função encontrada na internet para ordenação de Maps by values
    public static HashMap<Image, Float> sortByValue(HashMap<Image, Float> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Image, Float> > list = 
               new LinkedList<Map.Entry<Image, Float> >(hm.entrySet()); 
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Image, Float> >() { 
            public int compare(Map.Entry<Image, Float> o1,  
                               Map.Entry<Image, Float> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
        // put data from sorted list to hashmap  
        HashMap<Image, Float> temp = new LinkedHashMap<Image, Float>(); 
        for (Entry<Image, Float> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
    
	@Override
	public boolean isSomething(String something) {
		if(something.equalsIgnoreCase("person")) {
			return true;
		} else {
			return false;
		}
	}
	
}
