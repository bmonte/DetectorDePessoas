package br.ufrn.imd.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import com.github.sarxos.webcam.Webcam;

import br.ufrn.imd.DetectorDePessoas;
import br.ufrn.imd.modelo.DataController;
import br.ufrn.imd.modelo.WebcamInfo;

public class WebcamController implements Initializable {

	@FXML
	Button btnStartCamera;

	@FXML
	Button btnStopCamera;

	@FXML
	Button btnReturn;
	
	@FXML
	Button btnTakePicture;

	@FXML
	ComboBox<WebcamInfo> cbCameraOptions;

	@FXML
	BorderPane bpWebCamPaneHolder;

	@FXML
	FlowPane fpBottomPane;

	@FXML
	ImageView imgWebCamCapturedImage;

	private final String PATH = "./data/picture.png";
	
	private BufferedImage grabbedImage;
	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private boolean picture = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
	private DataController data_controller = new DataController(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DetectorDePessoas.addOnChangeScreenListener(new DetectorDePessoas.OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, DataController data){
				if(newScreen.equals("webcam_preview")) {
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
		
		fpBottomPane.setDisable(true);
		ObservableList<WebcamInfo> options = FXCollections.observableArrayList();
		int webCamCounter = 0;
		for (Webcam webcam : Webcam.getWebcams()) {
			WebcamInfo webCamInfo = new WebcamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter++;
		}
		cbCameraOptions.setItems(options);
		cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebcamInfo>() {

			@Override
			public void changed(ObservableValue<? extends WebcamInfo> arg0, WebcamInfo arg1, WebcamInfo arg2) {
				if (arg2 != null) {
					initializeWebCam(arg2.getWebCamIndex());
				}
			}
		});
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setImageViewSize();
			}
		});
	}
	
	protected void setImageViewSize() {

		double height = bpWebCamPaneHolder.getHeight();
		double width = bpWebCamPaneHolder.getWidth();
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		imgWebCamCapturedImage.setPreserveRatio(true);

	}

	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (selWebCam == null) {
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				} else {
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}
				startWebCamStream();
				return null;
			}

		};

		new Thread(webCamIntilizer).start();
		fpBottomPane.setDisable(false);
		btnStartCamera.setDisable(true);
	}

	protected void startWebCamStream() {

		stopCamera = false;
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
					try {
						if ((grabbedImage = selWebCam.getImage()) != null) {

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
										.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return null;
			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);

	}

	private void closeCamera() {
		if (selWebCam != null) {
			selWebCam.close();
		}
	}

	public void stopCamera(ActionEvent event) {
		stopCamera = true;
		closeCamera();
		btnStartCamera.setDisable(false);
		btnStopCamera.setDisable(true);
	}

	public void startCamera(ActionEvent event) {
		stopCamera = false;
		startWebCamStream();
		btnStartCamera.setDisable(true);
		btnStopCamera.setDisable(false);
	}

	public void returnToInput(ActionEvent event) {
		if (picture) {
			data_controller.setPath_webcam_picture(PATH);
		}
		
		DetectorDePessoas.changeScreen("input_settings", data_controller);
		//Chama a função para parar a execução da webcam
		stopCamera(event);
	}
	
	public void takePicture(ActionEvent event) {
		BufferedImage image = selWebCam.getImage();
		try {
			ImageIO.write(image, "PNG", new File(PATH));
			picture = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
