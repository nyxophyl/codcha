package helper;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Component;

import exc.ImageNotFoundException;

@Component
public class FileSelector {
	private final static String IMAGES_PATH_NAME = "images";

	public File[] getRGBFiles(String[] rgbFilenames) {
		File[] rgbFiles = new File[rgbFilenames.length];
		File dir = new File(IMAGES_PATH_NAME);
		for(int i = 0; i < rgbFilenames.length; i++) {
			if(rgbFilenames[i] != null) {
				FileFilter fileFilter = new WildcardFileFilter(rgbFilenames[i]);
				File[] files = dir.listFiles(fileFilter);
				if(files.length > 0) {
					rgbFiles[i] = files[0];
				} else {
					throw new ImageNotFoundException("Image(s) for given parameter not available.");
				}
			}
		}
		return rgbFiles;
	}

}
