package helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Component;

import exc.ImageNotFoundException;

@Component
public class ImageHandler {
	private final static String IMAGES_PATH_NAME = "images";

	public byte[] getJPGByFilenames(String[] rgbFilenames) {
		File[] rgbFiles = getRGBFiles(rgbFilenames);
		BufferedImage[] rgbBufferedImages = getBufferedImages(rgbFiles);
		return composeJPG(rgbBufferedImages);
	}

	private byte[] composeJPG(BufferedImage[] rgbBufferedImages) {
		// blue (index 2) is always available
		int width = rgbBufferedImages[2].getWidth();
		int height = rgbBufferedImages[2].getHeight();
		BufferedImage rgbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int alpha = 0xFF;
		for (int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int red = rgbBufferedImages[0] != null ? rgbBufferedImages[0].getRGB(x, y) & 0xFF : 0;
				int green = rgbBufferedImages[1] != null ? rgbBufferedImages[1].getRGB(x, y) & 0xFF : 0;
				int blue = rgbBufferedImages[2].getRGB(x, y) & 0xFF;
				rgbImage.setRGB(x, y, (alpha << 24) | (red << 16) | (green << 8) | blue);
			}
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] imageByteArray;
		try {
			ImageIO.write( rgbImage, "jpg", byteArrayOutputStream );
			byteArrayOutputStream.flush();
			imageByteArray = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			throw new ImageNotFoundException("Output image could not be created.");
		}
		return imageByteArray;
	}

	private BufferedImage[] getBufferedImages(File[] rgbFiles) {
		BufferedImage[] bufferedImages = new BufferedImage[rgbFiles.length];
		for(int i = 0; i < rgbFiles.length; i++) {
			if(rgbFiles[i] != null) {
				try {
					bufferedImages[i] = ImageIO.read(rgbFiles[i]);
				} catch (IOException e) {
					throw new ImageNotFoundException("Image(s) for given parameter not accessible.");
				}
			}
		}
		return bufferedImages;
	}

	private File[] getRGBFiles(String[] rgbFilenames) {
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
