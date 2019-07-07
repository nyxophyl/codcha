package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import main.Application;

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
@ContextConfiguration(classes = {Application.class})
class ImageControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	void testImageNotFound() throws Exception {
		this.mvc.perform(post("/generate-image").contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" + 
                		"\"utmZone\": 33,\r\n" + 
                		"\"latitudeBand\": \"U\",\r\n" + 
                		"\"gridSquare\": \"UP\",\r\n" + 
                		"\"date\": \"20180804\",\r\n" + 
                		"\"channelMap\": \"waterVapor\"\r\n" + 
                		"}")).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
//                        .andExpect(jsonPath("$.message").value("Image(s) for given parameter not available."));
	}

	// ignore this test, it only works, when the file T33UUP_20180804T100031_B09.tif is
	// in the images folder.
	@Ignore
	void testOk() throws Exception {
		this.mvc.perform(post("/generate-image").contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" + 
                		"\"utmZone\": 33,\r\n" + 
                		"\"latitudeBand\": \"U\",\r\n" + 
                		"\"gridSquare\": \"UP\",\r\n" + 
                		"\"date\": \"2018-08-04\",\r\n" + 
                		"\"channelMap\": \"waterVapor\"\r\n" + 
                		"}")).andExpect(status().isOk());
	}
}
