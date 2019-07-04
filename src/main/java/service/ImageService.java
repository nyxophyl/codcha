package service;

import org.springframework.stereotype.Service;

import data.RequestDTO;

@Service
public class ImageService {
	public byte[] generateImage(RequestDTO requestDto) throws ImageNotFoundException {
		System.out.println("requestDto: " + requestDto.toString());
		throw new ImageNotFoundException();
	}
}
