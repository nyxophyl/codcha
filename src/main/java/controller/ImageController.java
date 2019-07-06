package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.RequestDTO;
import service.ImageService;

@RestController
@RequestMapping(value = "/")
public class ImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/generate-image", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateImage(@RequestBody RequestDTO requestDto) {
        return imageService.generateImage(requestDto);
    }
}
