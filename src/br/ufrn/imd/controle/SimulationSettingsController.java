package br.ufrn.imd.controle;

import br.ufrn.imd.DetectorDePessoas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class SimulationSettingsController {
	
	@FXML private RadioButton lamp_on;
	@FXML private RadioButton lamp_off;
	@FXML private RadioButton tv_on;
	@FXML private RadioButton tv_off;
	@FXML private RadioButton ac_on;
	@FXML private RadioButton ac_off;
	
	private DataController data_controller = new DataController();
	
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, DataController data){
				if(newScreen.equals("simulation_settings")) {
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
	
	@FXML public void update(ActionEvent event) {
		data_controller.setLamp_on(lamp_on.isSelected());
		data_controller.setLamp_off(lamp_off.isSelected());
		data_controller.setTv_on(tv_on.isSelected());
		data_controller.setTv_off(tv_off.isSelected());
		data_controller.setAc_on(ac_on.isSelected());
		data_controller.setAc_off(ac_off.isSelected());
	}

	@FXML public void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main", data_controller);
	}
	@FXML public void btSSettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("simulation_settings", data_controller);
	}
	@FXML public void btISettings(ActionEvent event) {
		DetectorDePessoas.changeScreen("input_settings", data_controller);
	}
	@FXML public void btAbout(ActionEvent event) {
		DetectorDePessoas.changeScreen("about", data_controller);
	}

}
