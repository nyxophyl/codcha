package helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exc.ImageNotFoundException;

@Component
public class ImageHandler {
	public static final int RED_INDEX = 0;
	public static final int GREEN_INDEX = 1;
	public static final int BLUE_INDEX = 2;

	@Autowired
	private FileSelector fileSelector;

	public byte[] getJPGByFilenames(String[] rgbFilenames) {
		File[] rgbFiles = fileSelector.getRGBFiles(rgbFilenames);
		BufferedImage[] rgbBufferedImages = getBufferedImages(rgbFiles);
		return composeJPG(rgbBufferedImages);
	}

	/**
	 * This method generates the RGB jpg image from the given buffered images.
	 * For this, the lowest byte of each rgb information of the buffered images
	 * will be used for the certain color information. This can be done, because
	 * the higher two bytes of the rgb integers hold the same value.
	 * @param rgbBufferedImages the buffered images for RGB
	 * @return the jpg image as byte array
	 */
	private byte[] composeJPG(BufferedImage[] rgbBufferedImages) {
		// blue (index 2) is always available
		int width = rgbBufferedImages[BLUE_INDEX].getWidth();
		int height = rgbBufferedImages[BLUE_INDEX].getHeight();
		BufferedImage rgbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int alpha = 0xFF;
		for (int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int red = rgbBufferedImages[RED_INDEX] != null ? rgbBufferedImages[RED_INDEX].getRGB(x, y) & 0xFF : 0;
				int green = rgbBufferedImages[GREEN_INDEX] != null ? rgbBufferedImages[GREEN_INDEX].getRGB(x, y) & 0xFF : 0;
				int blue = rgbBufferedImages[BLUE_INDEX].getRGB(x, y) & 0xFF;
				rgbImage.setRGB(x, y, (alpha << 24) | (red << 16) | (green << 8) | blue);
			}
		}
		byte[] imageByteArray;
		try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			ImageIO.write( rgbImage, "jpg", byteArrayOutputStream );
			byteArrayOutputStream.flush();
			imageByteArray = byteArrayOutputStream.toByteArray();
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
}
