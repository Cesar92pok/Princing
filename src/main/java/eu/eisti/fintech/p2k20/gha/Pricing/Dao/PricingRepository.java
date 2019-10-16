package eu.eisti.fintech.p2k20.gha.Pricing.Dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eu.eisti.fintech.p2k20.gha.Pricing.model.Pricing;

@Repository
public interface PricingRepository extends CrudRepository<Pricing, Integer>{
   
	List<Pricing> findByMinAmountLessThanEqualAndMaxAmountGreaterThanEqualAndMinDurationLessThanEqualAndMaxDurationGreaterThanEqual(BigDecimal amountA, BigDecimal amountB, int durationA, int durationB);
	
	default List<Pricing> findByAmountAndDuration(BigDecimal amount, int duration) {
		return findByMinAmountLessThanEqualAndMaxAmountGreaterThanEqualAndMinDurationLessThanEqualAndMaxDurationGreaterThanEqual(amount, amount, duration, duration);
	}
	
}
