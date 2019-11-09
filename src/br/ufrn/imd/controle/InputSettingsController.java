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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class InputSettingsController {
	
	@FXML RadioButton insert_file_radio;
	@FXML RadioButton webcam_radio;
		//INSERT FILE
		@FXML private Pane insert_image_pane;
		@FXML private Button file_chooser;
		@FXML private Label warning_label;
		@FXML private ImageView image_view;
		private File selected_file;
		
	@FXML public void btSimulation(ActionEvent event) {
		DetectorDePessoas.changeScreen("main", selected_file);
	}
	@FXML public void btSSettings() {
		DetectorDePessoas.changeScreen("simulation_settings");
	}
	@FXML public void btISettings() {
		DetectorDePessoas.changeScreen("settings");
	}
	@FXML public void btAbout() {
		DetectorDePessoas.changeScreen("about");
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
			} else {
				warning_label.setText("Tipo inv�lido");
			}
		}
	}

}
