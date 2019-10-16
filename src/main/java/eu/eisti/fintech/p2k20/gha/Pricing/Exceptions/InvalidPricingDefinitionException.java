package eu.eisti.fintech.p2k20.gha.Pricing.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPricingDefinitionException extends PricingException{
	public InvalidPricingDefinitionException(String message) {
		super(message);
	}

}
