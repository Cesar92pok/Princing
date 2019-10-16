package eu.eisti.fintech.p2k20.gha.Pricing.Exceptions;

public abstract class PricingException extends RuntimeException {
   
	public PricingException(String message) {
		super(message);
	}
}
