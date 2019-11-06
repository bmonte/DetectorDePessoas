package br.ufrn.imd.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Class CSV
 * 
 * Classe responsável pela leitura de um arquivo
 * em formato CSV (dataset).
 * 
*/
public class CSV implements TypeVerify{
	
	private final String divider = ",";
	
	private BufferedReader reader;
	private String path;
	//Atributos de uma instancia
	private ArrayList<Float> attributes;
	private String label;
	
	public CSV() {
		attributes = new ArrayList<Float>();
	}
	
	//Getters and Setters
	//Array precisa ser reiniciada sempre que chamada
	public ArrayList<Float> getAttributes() {
		ArrayList<Float> returnable = attributes;
		this.attributes = new ArrayList<Float>();
		return returnable;	
	}

	public String getLabel() {
		return label;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		try {
			reader = new BufferedReader(new FileReader(path));
			//Pula o header
			reader.readLine();
			//Se deu certo guarda o caminho
			this.path = path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Função que lê uma linha do arquivo CSV
	public boolean readInstance() throws IOException {
		
		String line = reader.readLine();
		if (line == null) {
			return false;
		}
		String[] instance = line.split(divider);
		
		for(String cell : instance) {
			if (this.isSomething(cell)) {
				attributes.add(Float.valueOf(cell));
			} else {
				label = cell;
			}
		}
		return true;
	}
	
	@Override
	public boolean isSomething(String something) {
		try {
			Float.parseFloat(something);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
