package br.ufrn.imd.modelo;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Ext{
	private static String[] allowed_extensions = new String[] {".png", ".jpeg", ".jpg", ".gif"};
	
	public static boolean verifyExtension(String extension) {
		List<String> list = Arrays.asList(allowed_extensions);
		
		if(list.contains(extension)) {
			return true;
		} else {
			return false;
		}
	}
	public static String getExtension(File file) {
        String extension;
		try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            } else {
            	extension = "";
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
	}

}
