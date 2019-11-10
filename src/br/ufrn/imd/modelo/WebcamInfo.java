package br.ufrn.imd.modelo;

public class WebcamInfo {

	private String webCamName;
	private int webCamIndex;

	public void setWebCamName(String webCamName) {
		this.webCamName = webCamName;
	}

	public int getWebCamIndex() {
		return webCamIndex;
	}

	public void setWebCamIndex(int webCamIndex) {
		this.webCamIndex = webCamIndex;
	}

	@Override
	public String toString() {
		return webCamName;
	}
}