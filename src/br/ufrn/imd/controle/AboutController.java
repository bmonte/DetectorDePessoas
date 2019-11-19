package br.ufrn.imd.controle;

import br.ufrn.imd.DetectorDePessoas;
import br.ufrn.imd.modelo.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AboutController {

	private DataController data_controller = new DataController(); 

	
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, DataController data){
				if(newScreen.equals("about")) {
					data_controller.setSelected_file(data.getSelected_file());
					data_controller.setPath_webcam_picture(data.getPath_webcam_picture());
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
	

}
