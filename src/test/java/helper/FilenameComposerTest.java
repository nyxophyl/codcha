package helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import data.RequestDTO;

class FilenameComposerTest {

	private static FilenameComposer filenameComposer;

	@BeforeAll
	public static void setUp() {
		filenameComposer = new FilenameComposer();
	}

	@Test
	void testValidVisibleRequest() {
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-05", "visible");
		String[] filenames = filenameComposer.getRGBFilenames(requestDto);
		assertEquals("T42UUP_20190705*_B04.tif", filenames[ImageHandler.RED_INDEX]);
		assertEquals("T42UUP_20190705*_B03.tif", filenames[ImageHandler.GREEN_INDEX]);
		assertEquals("T42UUP_20190705*_B02.tif", filenames[ImageHandler.BLUE_INDEX]);
	}

	@Test
	void testValidVegetationRequest() {
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-05", "vegetation");
		String[] filenames = filenameComposer.getRGBFilenames(requestDto);
		assertEquals("T42UUP_20190705*_B05.tif", filenames[ImageHandler.RED_INDEX]);
		assertEquals("T42UUP_20190705*_B06.tif", filenames[ImageHandler.GREEN_INDEX]);
		assertEquals("T42UUP_20190705*_B07.tif", filenames[ImageHandler.BLUE_INDEX]);
	}

	@Test
	void testValidWaterVaporRequest() {
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-05", "waterVapor");
		String[] filenames = filenameComposer.getRGBFilenames(requestDto);
		assertNull(filenames[ImageHandler.RED_INDEX]);
		assertNull(filenames[ImageHandler.GREEN_INDEX]);
		assertEquals("T42UUP_20190705*_B09.tif", filenames[ImageHandler.BLUE_INDEX]);
	}

	@Test
	void testInvalidChannelMapRequest() {
		RequestDTO requestDto = new RequestDTO(42, "U", "UP", "2019-07-05", "mixed");
		assertThrows(IllegalArgumentException.class, () -> filenameComposer.getRGBFilenames(requestDto));
	}
}
