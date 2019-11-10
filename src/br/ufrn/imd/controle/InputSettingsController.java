package br.ufrn.imd.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.DetectorDePessoas;
import br.ufrn.imd.modelo.Ext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class InputSettingsController {
	
	@FXML RadioButton insert_file_radio;
	@FXML RadioButton webcam_radio;
	@FXML ToggleGroup inputToggle;
		//INSERT FILE
		@FXML private Pane insert_image_pane;
		@FXML private Pane webcam_pane;
		@FXML private Button file_chooser;
		@FXML private Button take_picture;
		@FXML private Label warning_label;
		@FXML private ImageView image_view;
		@FXML private ImageView imgWebCam;
		private File selected_file;
		private DataController data_controller = new DataController();
		
	@FXML protected void initialize() {
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, DataController data){;
				if(newScreen.equals("input_settings")) {
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
				
				//Verifica se existe uma imagem tirada pela webcam
				try {
					webcamPreview();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	@FXML public void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main", data_controller);
	}
	@FXML public void btSSettings() {
		DetectorDePessoas.changeScreen("simulation_settings", data_controller);
	}
	@FXML public void btISettings() {
		DetectorDePessoas.changeScreen("input_settings", data_controller);
	}
	@FXML public void btAbout() {
		DetectorDePessoas.changeScreen("about", data_controller);
	}
	@FXML public void btTakePicture() {
		DetectorDePessoas.changeScreen("webcam_preview", data_controller);
	}
	
	@FXML public void inputFile(ActionEvent event) throws FileNotFoundException {
		FileChooser fc = new FileChooser();
		selected_file = fc.showOpenDialog(null);
		if(selected_file != null) {
			String extension = Ext.getExtension(selected_file);
			if(Ext.verifyExtension(extension)) {
				Image image = new Image(new FileInputStream(selected_file.getPath()));
				image_view.setImage(image);
				warning_label.setText(selected_file.getName());
				data_controller.setSelected_file(selected_file);
			} else {
				warning_label.setText("Tipo inválido");
			}
		}
	}
	
	@FXML public void webcamPreview() throws FileNotFoundException {
		if (data_controller.getPath_webcam_picture() != null) {
			Image image = new Image(new FileInputStream(data_controller.getPath_webcam_picture()));
			imgWebCam.setImage(image);
			warning_label.setText(selected_file.getName());
		}
	}
	
	public void show(ActionEvent event) {
		if (inputToggle.getSelectedToggle().equals(insert_file_radio)) {
			webcam_pane.setVisible(false);
			insert_image_pane.setVisible(true);
			data_controller.setInput_chosen("insert_file");
		} else if (inputToggle.getSelectedToggle().equals(webcam_radio)) {
			insert_image_pane.setVisible(false);
			webcam_pane.setVisible(true);
		}
	}
}
