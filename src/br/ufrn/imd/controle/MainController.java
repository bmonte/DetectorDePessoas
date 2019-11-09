package br.ufrn.imd.controle;

import java.io.File;
import java.io.IOException;

import br.ufrn.imd.DetectorDePessoas;
import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.DistanciaChebychev;
import br.ufrn.imd.modelo.DistanciaEuclidiana;
import br.ufrn.imd.modelo.DistanciaManhattan;
import br.ufrn.imd.modelo.ImageHOG;
import br.ufrn.imd.modelo.Knn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MainController {
@FXML private BorderPane telaSimulation;
	
	@FXML private RadioButton euclidiana_radio_button;
	@FXML private RadioButton manhattan_radio_button;
	@FXML private RadioButton chebychev_radio_button;
	@FXML private Button calculate_button;
	@FXML private Slider k_slider;
	@FXML private Label k_label;
	@FXML private Label status_label;
	
	private Knn knn;
	private File selected_file;
	private String radio_button;
	
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, Object data){;
				if(newScreen.equals("main")) {
					selected_file = (File)data;
				}
			}
		});
	}
	@FXML protected void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main");
	}
	@FXML protected void btSSettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("simulation_settings");
	}
	@FXML protected void btISettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("input_settings");
	}
	@FXML protected void btAbout(ActionEvent event) {
		DetectorDePessoas.changeScreen("about");
	}
	
	@FXML public void startSimulation(ActionEvent event) throws IOException {
		try {
			CSV file = new CSV();
			file.setPath("./data/dataset_2019_1.csv");
			DatasetDAO dataset = new DatasetDAO();
			dataset.setFile(file);
			dataset.fillDataset();
			calculateOptions();
			ImageHOG image = new ImageHOG();
			image.setPath(selected_file.getPath());
			knn.setDataset(dataset.getDataset());
			knn.setImageTest(image.extract());
			knn.setK((int) k_slider.getValue());
			if(knn.calculate()) {
				status_label.setText("Pessoa identificada");
			} else {
				status_label.setText("Pessoa não encontrada");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    @FXML
    public void change(MouseEvent event) 
    {      
    	  k_label.setText("K: " + (int) k_slider.getValue());
    }
	
	private void calculateOptions() {
		switch(radio_button) {
			case "euclidiana":
				knn = new DistanciaEuclidiana();
			break;
			case "manhattan":
				knn = new DistanciaManhattan();
			break;
			case "chebychev":
				knn = new DistanciaChebychev();
			break;
		}
		
	}
	@FXML public void euclidiana() {
		radio_button = "euclidiana";
	}
	
	@FXML public void manhattan() {
		radio_button = "manhattan";
	}
	
	@FXML public void chebychev() {
		radio_button = "chebychev";
	}

}
