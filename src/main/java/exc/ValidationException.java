package exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ValidationException extends RuntimeException {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 3070550790442574758L;

	public ValidationException(String message) {
		super(message);
	}
}
