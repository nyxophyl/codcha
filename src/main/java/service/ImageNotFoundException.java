package service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Image(s) for given parameter not found.")
public class ImageNotFoundException extends RuntimeException {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = -6889861268774835334L;
}
