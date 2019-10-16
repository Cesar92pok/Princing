package eu.eisti.fintech.p2k20.gha.Pricing.Service;

import java.util.List;

import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.InvalidPricingDefinitionException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingIdDuplicationException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingNotFoundException;
import eu.eisti.fintech.p2k20.gha.Pricing.model.Pricing;

public interface PricingService {
	
	public void addPricing(Pricing pricing)
	         throws InvalidPricingDefinitionException, PricingIdDuplicationException;
	
	public Pricing getPricingById(int pricingId)
	         throws PricingNotFoundException;
	public List<Pricing> getListPricing()
	         throws PricingNotFoundException;
	public void removePricingById (int id)
	         throws PricingNotFoundException ;

}
