package eu.eisti.fintech.p2k20.gha.Pricing.Service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.eisti.fintech.p2k20.gha.Pricing.Dao.PricingRepository;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.InvalidPricingDefinitionException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingIdDuplicationException;
import eu.eisti.fintech.p2k20.gha.Pricing.Exceptions.PricingNotFoundException;
import eu.eisti.fintech.p2k20.gha.Pricing.Service.PricingService;
import eu.eisti.fintech.p2k20.gha.Pricing.model.Pricing;

@Service
public class PricingServiceImpl implements PricingService{
	
	@Autowired
	PricingRepository pricingRepository;
	
	@Override
	public void addPricing(Pricing pricing)
	         throws PricingIdDuplicationException, InvalidPricingDefinitionException {
		if (pricing.getMinDuration() > pricing.getMaxDuration()){
			
			throw new InvalidPricingDefinitionException("Max duration should be greater than min duration");
		}
		if (pricing.getMaxAmount().compareTo(pricing.getMinAmount()) < 0) {
			throw new InvalidPricingDefinitionException("Max amount should be greater than min amount");
		}
		if (pricing.getRate() == null || pricing.getRate().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidPricingDefinitionException("Rate should be positive");
		}
		if (pricingRepository.findById(pricing.getId()).isPresent()) {
			throw new PricingIdDuplicationException("Duplicate Pricing Id");
		}
		pricingRepository.save(pricing);
	}
	
	@Override
	public Pricing getPricingById(int pricingId)
	         throws PricingNotFoundException {
		
		Optional<Pricing> pricing = pricingRepository.findById(pricingId);
		if (pricing.isPresent()) {
			return pricing.get();
		}
		throw new PricingNotFoundException("Pricing not found with Id "+ pricingId);
	}
	
	@Override
	public List<Pricing> getListPricing()
	         throws PricingNotFoundException{
		Iterator<Pricing> liste = pricingRepository.findAll().iterator();
		if (liste.hasNext() == false) {
			throw new PricingNotFoundException("There is no pricing");
		}
		List<Pricing> response = new ArrayList<Pricing>();
		while (liste.hasNext() == true) {
			response.add(liste.next());
		}
		return response;
		
	}
	
	@Override
	public void removePricingById(int id)
	         throws PricingNotFoundException {

		if(!pricingRepository.existsById(id)) throw new PricingNotFoundException("There is no pricing"); 
		pricingRepository.deleteById(id);
			
	}
				
}
