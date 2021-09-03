package br.com.landisgyr.energyconsumption.service;

import br.com.landisgyr.energyconsumption.model.Meter;
import br.com.landisgyr.energyconsumption.exception.MeterControllerException;
import br.com.landisgyr.energyconsumption.exception.BillingCalculationException;

public interface EnergyConsumptionService {

	Double calculateBilling(Long consumption, Long microgeneration, Double unit) throws BillingCalculationException;
	
	Meter findById(String meterNo) throws MeterControllerException;
	
	Meter save(Meter meter) throws MeterControllerException;
	
	Meter saveMeter(String meter) throws MeterControllerException;

}