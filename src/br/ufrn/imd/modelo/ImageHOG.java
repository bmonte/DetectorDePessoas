package br.ufrn.imd.modelo;

import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

public class ImageHOG {
		static {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		}
		
		private String path;
		private List<Float> arrayOfFeatures;
		
		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public List<Float> getArrayOfFeatures() {
			return arrayOfFeatures;
		}
		
		public List<Float> extract() {
			HOGDescriptor hog = new HOGDescriptor();
			Mat img = new Mat();
			MatOfFloat features = new MatOfFloat();
			img = Imgcodecs.imread(this.path, Imgcodecs.IMREAD_GRAYSCALE);
			Imgproc.resize(img, img, new Size(64,128), 0.5, 0.5, Imgproc.INTER_LINEAR);
			hog.compute(img,features);
			this.arrayOfFeatures = features.toList();
			return this.arrayOfFeatures;
		}

		
}