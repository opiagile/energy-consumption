package br.com.landisgyr.energyconsumption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.landisgyr.energyconsumption.dto.MeterDTO;
import br.com.landisgyr.energyconsumption.exception.BillingCalculationException;
import br.com.landisgyr.energyconsumption.exception.MeterControllerException;
import br.com.landisgyr.energyconsumption.model.Meter;
import br.com.landisgyr.energyconsumption.service.EnergyConsumptionService;
import br.com.landisgyr.energyconsumption.vo.MeterVO;

@RestController
@RequestMapping(value = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

	@Autowired
	EnergyConsumptionService energyConsumptionService;

	public EventController(EnergyConsumptionService energyConsumptionService) {
		this.energyConsumptionService = energyConsumptionService;
	}

	@PostMapping
	public ResponseEntity<Object> processEvent(@RequestBody MeterDTO meterDTO) throws BillingCalculationException, MeterControllerException {

		ResponseEntity<Object> response = null;
		
		if (meterDTO.getMeterNumber() == null || meterDTO.getMeterNumber().isBlank()) {
			return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
		}
		
		Meter meter = energyConsumptionService.findById(meterDTO.getMeterNumber());
		
		switch (meterDTO.getType()) {
        case "import":
        	response = importMeter(meterDTO); 
            break;
        case "push":
        	response = pushMeter(meterDTO, meter);
            break;
        case "billing":
        	response = billingMeter(meterDTO, meter);
            break;
        default:
        	response = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            break;
		}		

		return response;
	}

	private ResponseEntity<Object> importMeter(MeterDTO meterDTO) throws MeterControllerException {
		
		Meter meter = energyConsumptionService.saveMeter(meterDTO.getMeterNumber());

		return new ResponseEntity<>(new MeterVO(meter), HttpStatus.CREATED);
	}

	private ResponseEntity<Object> pushMeter(MeterDTO meterDTO, Meter meter) {

		if (meter == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		meter.setConsumption(meterDTO.getActiveEnergy());
		meter.setMicrogeneration(meterDTO.getInjectedEnergy());

		return new ResponseEntity<>(new MeterVO(meter), HttpStatus.CREATED);
	}
		
	@SuppressWarnings("unused")
	private ResponseEntity<Object> billingMeter(final MeterDTO meterDTO, final Meter meter) throws BillingCalculationException {

		if (meter == null) {
			return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
		}

		Double cash = energyConsumptionService.calculateBilling(meter.getConsumption(), meter.getMicrogeneration(), meterDTO.getUnit());
		return new ResponseEntity<>(new MeterVO(meter), HttpStatus.CREATED);
	}

}