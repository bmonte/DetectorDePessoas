package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.DetectorDePessoas;
import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.DataController;
import br.ufrn.imd.modelo.DistanciaChebychev;
import br.ufrn.imd.modelo.DistanciaEuclidiana;
import br.ufrn.imd.modelo.DistanciaManhattan;
import br.ufrn.imd.modelo.ImageHOG;
import br.ufrn.imd.modelo.Knn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	@FXML private ListView<String> list_view;
	
	private Knn knn;
	private int count_simulation = 0;
	private String radio_button = "manhattan";
	private boolean is_person;
	private boolean log_lamp = false;
	private boolean log_tv = false;
	private boolean log_ac = false;
	private DataController data_controller = new DataController();
	
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, DataController data){
				if(newScreen.equals("main")) {
					data_controller.setSelected_file(data.getSelected_file());
					data_controller.setPath_webcam_picture(data.getPath_webcam_picture());
					if (data.getPath_webcam_picture() != null || data.getSelected_file() != null) {
						calculate_button.setDisable(false);
					}
					data_controller.setInput_chosen(data.getInput_chosen());
					data_controller.setLamp_on(data.isLamp_on());
					data_controller.setLamp_off(data.isLamp_off());
					data_controller.setTv_on(data.isTv_on());
					data_controller.setTv_off(data.isTv_off());
					data_controller.setAc_on(data.isAc_on());
					data_controller.setAc_off(data.isAc_off());
				}
			}
		});
	}
	@FXML protected void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main", data_controller);
	}
	@FXML protected void btSSettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("simulation_settings", data_controller);
	}
	@FXML protected void btISettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("input_settings", data_controller);
	}
	@FXML protected void btAbout(ActionEvent event) {
		DetectorDePessoas.changeScreen("about", data_controller);
	}
	
	@FXML public void startSimulation(ActionEvent event) throws IOException {
		try {
			CSV file = new CSV();
			file.setPath("./data/teste_2019_1.csv");
			DatasetDAO dataset = new DatasetDAO();
			dataset.setFile(file);
			dataset.fillDataset();
			calculateOptions();
			ImageHOG image = new ImageHOG();
			if(data_controller.getInput_chosen() == "insert_file") {
				image.setPath(data_controller.getSelected_file().getPath());
			} else {
				image.setPath(data_controller.getPath_webcam_picture());
			}
			knn.setDataset(dataset.getDataset());
			knn.setImageTest(image.extract());
			knn.setK((int) k_slider.getValue());
			this.is_person = knn.calculate();
			simulation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void simulation() {
		this.count_simulation += 1;
		list_view.getItems().add(this.count_simulation + "th simulation");
		if(this.is_person) {
			status_label.setText("Result: Person");
			
			if(data_controller.isLamp_on() && !log_lamp) {
				log_lamp = true;
				list_view.getItems().add("Lamp On");
			}
			if(data_controller.isTv_on() && !log_tv) {
				log_tv = true;
				list_view.getItems().add("Tv On");
			}
			if(data_controller.isAc_on() && !log_ac) {
				log_ac = true;
				list_view.getItems().add("Ac On");
			}
		} else {
			status_label.setText("Result: No person");
			
			if(data_controller.isLamp_off() && log_lamp) {
				log_lamp = false;
				list_view.getItems().add("Lamp Off");
			}
			if(data_controller.isTv_off() && log_tv) {
				log_tv = false;
				list_view.getItems().add("Tv Off");
			}
			if(data_controller.isAc_off() && log_ac) {
				log_ac = false;
				list_view.getItems().add("Ac Off");
			}
		}
		list_view.getItems().add("");
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
