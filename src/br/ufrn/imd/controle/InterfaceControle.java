package br.ufrn.imd.controle;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.ufrn.imd.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class InterfaceControle {
	@FXML private AnchorPane anchor_pane;
	//SIMULATION
	@FXML private Button start;
	@FXML private Label status_label;
	//INPUT SETTINGS
	@FXML CheckBox insert_file_check;
	@FXML CheckBox webcam_check;
		//INSERT FILE
		@FXML private Pane insert_image_pane;
		@FXML private Button file_chooser;
		@FXML private Label warning_label;
		@FXML private ImageView image_view;
		private File selected_file;
	//SIMULATION SETTINGS
	
	
	@FXML
	public void inputFile(ActionEvent event) throws FileNotFoundException {
		FileChooser fc = new FileChooser();
		selected_file = fc.showOpenDialog(null);
		if(selected_file != null) {
			String extension = Ext.getExtension(selected_file);
			if(Ext.verifyExtension(extension)) {
				Image image = new Image(new FileInputStream(selected_file.getPath()));
				image_view.setImage(image);
				warning_label.setText(selected_file.getName());
				start.setDisable(false);
			} else {
				warning_label.setText("Tipo inv�lido");
			}
		}
	}
	
	@FXML
	public void startSimulation(ActionEvent event) throws IOException {
		try {
			CSV file = new CSV();
			file.setPath("./data/dataset_2019_1.csv");
			DatasetDAO dataset = new DatasetDAO();
			dataset.setFile(file);
			dataset.fillDataset();
			
			ImageHOG image = new ImageHOG();
			image.setPath(selected_file.getPath());
			Knn knn = new DistanciaManhattan();
			knn.setDataset(dataset.getDataset());
			knn.setImageTest(image.extract());
			knn.setK(3);
			if(knn.calculate()) {
				status_label.setText("pessoa identificada");
			} else {
				status_label.setText("pessoa não encontrada");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void selectInsertFile(ActionEvent event) {
		if(insert_file_check.isSelected()) {
			webcam_check.setSelected(false);
			insert_image_pane.setVisible(true);
		} else {
			insert_image_pane.setVisible(false);
		}
	}
	
	@FXML
	public void selectWebcam(ActionEvent event) {
		if(webcam_check.isSelected()) {
			insert_file_check.setSelected(false);
		} 
	}
}
