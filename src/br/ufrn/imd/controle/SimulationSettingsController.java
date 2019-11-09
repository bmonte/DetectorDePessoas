package br.ufrn.imd.controle;

import br.ufrn.imd.DetectorDePessoas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SimulationSettingsController {

	@FXML public void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main");
	}
	@FXML public void btSSettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("simulation_settings");
	}
	@FXML public void btISettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("input_settings");
	}
	@FXML public void btAbout(ActionEvent event) {
		DetectorDePessoas.changeScreen("about");
	}

}
