package helper;

import org.springframework.stereotype.Component;

import data.ChannelMap;
import data.RequestDTO;

@Component
public class FilenameComposer {
	/**
	 * Composes the filename from the given requestDto. The filename has the format:
	 * T[UTM_ZONE][LATITUDE_BAND][GRID_SQUARE]_[ACQUISITION_DATETIME]_[SENSOR_BAND].tif.
	 * The unknown time part will be marked as '*'.
	 * @param requestDto the RequestDTO
	 * @return the resulting RGBFilenames
	 */
	public String[] getRGBFilenames(RequestDTO requestDto) {
		ChannelMap channelMap = ChannelMap.valueOf(requestDto.getChannelMap());
		String[] rgbFilenames = new String[channelMap.getRgbPostfixes().length];
		for(int i = 0; i < rgbFilenames.length; i++) {
			if(channelMap.getRgbPostfixes()[i] != null) {
				rgbFilenames[i] = "T" + requestDto.getUtmZone().toString() + requestDto.getLatitudeBand()
				+ requestDto.getGridSquare() + "_" + requestDto.getDate().replaceAll("-", "")
				+ "*_" + channelMap.getRgbPostfixes()[i] + ".tif";
			}
		}
		return rgbFilenames;
	}
}
