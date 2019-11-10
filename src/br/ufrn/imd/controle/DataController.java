package br.ufrn.imd.controle;

import java.io.File;

public class DataController {

	private File selected_file;
	private boolean lamp_on;
	private boolean lamp_off;
	private boolean tv_on;
	private boolean tv_off;
	private boolean ac_on;
	private boolean ac_off;
	private String input_chosen;
	private String path_webcam_picture;
	
	/**
	 * @return the selected_file
	 */
	public File getSelected_file() {
		return selected_file;
	}
	/**
	 * @param selected_file the selected_file to set
	 */
	public void setSelected_file(File selected_file) {
		this.selected_file = selected_file;
	}
	/**
	 * @return the lamp_on
	 */
	public boolean isLamp_on() {
		return lamp_on;
	}
	/**
	 * @param lamp_on the lamp_on to set
	 */
	public void setLamp_on(boolean lamp_on) {
		this.lamp_on = lamp_on;
	}
	/**
	 * @return the lamp_off
	 */
	public boolean isLamp_off() {
		return lamp_off;
	}
	/**
	 * @param lamp_off the lamp_off to set
	 */
	public void setLamp_off(boolean lamp_off) {
		this.lamp_off = lamp_off;
	}
	/**
	 * @return the tv_on
	 */
	public boolean isTv_on() {
		return tv_on;
	}
	/**
	 * @param tv_on the tv_on to set
	 */
	public void setTv_on(boolean tv_on) {
		this.tv_on = tv_on;
	}
	/**
	 * @return the tv_off
	 */
	public boolean isTv_off() {
		return tv_off;
	}
	/**
	 * @param tv_off the tv_off to set
	 */
	public void setTv_off(boolean tv_off) {
		this.tv_off = tv_off;
	}
	/**
	 * @return the ac_on
	 */
	public boolean isAc_on() {
		return ac_on;
	}
	/**
	 * @param ac_on the ac_on to set
	 */
	public void setAc_on(boolean ac_on) {
		this.ac_on = ac_on;
	}
	/**
	 * @return the ac_off
	 */
	public boolean isAc_off() {
		return ac_off;
	}
	/**
	 * @param ac_off the ac_off to set
	 */
	public void setAc_off(boolean ac_off) {
		this.ac_off = ac_off;
	}
	
	public String getPath_webcam_picture() {
		return path_webcam_picture;
	}
	public void setPath_webcam_picture(String path_webcam_picture) {
		this.path_webcam_picture = path_webcam_picture;
	}
	
	public String getInput_chosen() {
		return input_chosen;
	}
	public void setInput_chosen(String input_chosen) {
		this.input_chosen = input_chosen;
	}

}
