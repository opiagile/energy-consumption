package br.com.landisgyr.energyconsumption.dao;

import br.com.landisgyr.energyconsumption.model.Meter;
import br.com.landisgyr.energyconsumption.service.EnergyConsumptionService;
import br.com.landisgyr.energyconsumption.exception.MeterControllerException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import br.com.landisgyr.energyconsumption.exception.BillingCalculationException;

@Component
public class EnergyConsumptionDAO implements  EnergyConsumptionService {

	private Map<String, Meter> meterData = new ConcurrentHashMap<>();
	

	@Override
	public Meter findById(String meterNo) throws MeterControllerException {
		if (meterNo.isBlank()) {
			throw new MeterControllerException("The field meterNo is mandatory.");
		}

		return meterData.get(meterNo);
	}

	@Override
	public Meter save(Meter meter) throws MeterControllerException {
		if (meter == null || meter.getMeterNo().isBlank()) {
			throw new MeterControllerException("Cannot save null.");
		}
		
		meterData.put(meter.getMeterNo(), meter);
		return meterData.get(meter.getMeterNo());
	}

	@Override
	public Meter saveMeter(String meterNumber) throws MeterControllerException {
		if (meterNumber.isBlank()) {
			throw new MeterControllerException("Cannot save when meter number is empty.");
		}
		
		Meter meter = new Meter(meterNumber, 0L, 0L);
		save(meter);
		
		return meter;
	}
	
	@Override
	public Double calculateBilling(Long consumption, Long microgeneration, Double unit) throws BillingCalculationException {
		if (consumption == null || microgeneration == null || unit == null) {
			throw new BillingCalculationException("Cannot calculate billing due to invalid input data.");
		}
		
		return (consumption - microgeneration) * unit;
	}

}