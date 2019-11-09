package br.ufrn.imd.controle;

import br.ufrn.imd.DetectorDePessoas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AboutController {

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
	

}
