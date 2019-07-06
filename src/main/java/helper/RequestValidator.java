package helper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import data.ChannelMap;
import data.RequestDTO;
import exc.ImageNotFoundException;
import exc.ValidationException;

@Component
public class RequestValidator {
	private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private Validator validator = validatorFactory.usingContext().getValidator();

	public void validateRequestDTO(RequestDTO requestDto) {

		StringBuilder builder = new StringBuilder();
		Set<ConstraintViolation<RequestDTO>> constraints = validator.validate(requestDto);
		for (ConstraintViolation<RequestDTO> constraint : constraints) {
			builder.append(
					"[" + constraint.getPropertyPath() + "][" + constraint.getMessage() + "]\n"
					);
		}
		if(builder.length() > 0) {
			throw new ValidationException(builder.toString());
		}
		
		try {
			ChannelMap.valueOf(requestDto.getChannelMap());
		}
		catch(IllegalArgumentException e) {
			throw new ImageNotFoundException("Parameter channelMap only allows following values: 'visible', 'vegatation' and 'waterVapor'");
		}
	}

}
