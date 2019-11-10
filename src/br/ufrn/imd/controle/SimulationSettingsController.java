package br.ufrn.imd.controle;

import java.io.File;

import br.ufrn.imd.DetectorDePessoas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class SimulationSettingsController {

	private File selected_file;
	
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, Object data){;
				if(newScreen.equals("simulation_settings")) {
					selected_file = (File)data;
				}
			}
		});
	}
	
	@FXML public void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main", selected_file);
	}
	@FXML public void btSSettings() {
		DetectorDePessoas.changeScreen("simulation_settings", selected_file);
	}
	@FXML public void btISettings() {
		DetectorDePessoas.changeScreen("settings", selected_file);
	}
	@FXML public void btAbout() {
		DetectorDePessoas.changeScreen("about", selected_file);
	}
}
