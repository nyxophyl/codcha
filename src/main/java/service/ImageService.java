package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.RequestDTO;
import helper.FilenameComposer;
import helper.ImageHandler;
import helper.RequestValidator;

@Service
public class ImageService {
	@Autowired
	RequestValidator requestValidator;

	@Autowired
	FilenameComposer filenameComposer;

	@Autowired
	ImageHandler imageHandler;

	public byte[] generateImage(RequestDTO requestDto) {
		requestValidator.validateRequestDTO(requestDto);
		String[] rgbFilenames = filenameComposer.getRGBFilenames(requestDto);
		return imageHandler.getJPGByFilenames(rgbFilenames);
	}
}
