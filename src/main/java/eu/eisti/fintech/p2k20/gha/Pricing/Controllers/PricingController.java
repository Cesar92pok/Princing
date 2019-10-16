package eu.eisti.fintech.p2k20.gha.Pricing.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.InvalidPricingDefinitionException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingIdDuplicationException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingNotFoundException;
import eu.eisti.fintech.p2k20.gha.Pricing.Service.PricingService;
import eu.eisti.fintech.p2k20.gha.Pricing.model.Pricing;

@RestController
public class PricingController {
	
	Logger LOG = LoggerFactory.getLogger(PricingController.class);
	
	@Autowired
	PricingService pricingService;
	
	@GetMapping("/pricing/{pricingId}")
	public Pricing getPricingById(@PathVariable int pricingId)
		     throws PricingNotFoundException {
		return pricingService.getPricingById(pricingId);
		    	 
		     }
	@PostMapping("/pricing")
	public void addPricing(@RequestBody Pricing pricing)
	         throws InvalidPricingDefinitionException, PricingIdDuplicationException {
		LOG.info("Added new pricing with ID "+ pricing.getId());
		pricingService.addPricing(pricing);
	}
	
	@GetMapping("/pricing")
	public List<Pricing> getListPricing() 
			throws PricingNotFoundException {
		return pricingService.getListPricing();
	}

}
