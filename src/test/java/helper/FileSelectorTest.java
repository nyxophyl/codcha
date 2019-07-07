package helper;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import data.RequestDTO;
import exc.ImageNotFoundException;

class FileSelectorTest {
	private static final String[] FILENAMES_TO_TOUCH = new String[] {
			"T42UUP_20190707T100031_B09.tif",
			"T42UUP_20190707T100031_B02.tif",
			"T42UUP_20190707T100031_B03.tif",
			"T42UUP_20190707T100031_B04.tif",
			"T42UUP_20190707T100031_B05.tif",
			"T42UUP_20190707T100031_B06.tif"
	};

	private static FileSelector fileSelector;

	@BeforeAll
	public static void setUp() throws IOException {
		fileSelector = new FileSelector();
		for(String filename : FILENAMES_TO_TOUCH) {
			File file = new File(FileSelector.IMAGES_PATH_NAME, filename);
			FileUtils.touch(file);
		}
	}

	@AfterAll
	public static void tearDown() throws IOException {
		for(String filename : FILENAMES_TO_TOUCH) {
			File file = new File(FileSelector.IMAGES_PATH_NAME, filename);
			file.delete();
		}
	}

	@Test
	void testOneFileAvailable() {
		String[] filenameToTest = new String[] {
				null,
				null,
				"T42UUP_20190707*_B09.tif"
		};
		File[] filesToCheck = fileSelector.getRGBFiles(filenameToTest);
		assertNull(filesToCheck[0]);
		assertNull(filesToCheck[1]);
		assertNotNull(filesToCheck[2]);
	}

	@Test
	void testThreeFilesAvailable() {
		String[] filenameToTest = new String[] {
				"T42UUP_20190707*_B04.tif",
				"T42UUP_20190707*_B03.tif",
				"T42UUP_20190707*_B02.tif"
		};
		File[] filesToCheck = fileSelector.getRGBFiles(filenameToTest);
		assertNotNull(filesToCheck[0]);
		assertNotNull(filesToCheck[1]);
		assertNotNull(filesToCheck[2]);
	}

	@Test
	void testThreeFilesOneFileMissing() {
		String[] filenameToTest = new String[] {
				"T42UUP_20190707*_B05.tif",
				"T42UUP_20190707*_B06.tif",
				"T42UUP_20190707*_B07.tif"
		};
		assertThrows(ImageNotFoundException.class, () -> fileSelector.getRGBFiles(filenameToTest));
	}

	@Test
	void testWithFileComposerFilesFound() {
		FilenameComposer fileComposer = new FilenameComposer();
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-07", "visible");
		File[] filesToCheck = fileSelector.getRGBFiles(fileComposer.getRGBFilenames(requestDto));
		assertNotNull(filesToCheck[0]);
		assertNotNull(filesToCheck[1]);
		assertNotNull(filesToCheck[2]);
	}

	@Test
	void testWithFileComposerFilesNotFound() {
		FilenameComposer fileComposer = new FilenameComposer();
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-06", "visible");
		assertThrows(ImageNotFoundException.class, () -> fileSelector.getRGBFiles(fileComposer.getRGBFilenames(requestDto)));
	}
}
