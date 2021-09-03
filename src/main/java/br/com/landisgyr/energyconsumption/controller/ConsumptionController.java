package br.com.landisgyr.energyconsumption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.landisgyr.energyconsumption.exception.MeterControllerException;
import br.com.landisgyr.energyconsumption.model.Meter;
import br.com.landisgyr.energyconsumption.service.EnergyConsumptionService;

@RestController
@RequestMapping
public class ConsumptionController extends ResponseEntityExceptionHandler {

	@Autowired
	EnergyConsumptionService service;

	public ConsumptionController(EnergyConsumptionService service) {
		this.service = service;
	}

	@GetMapping(value = "/consumption")
	public ResponseEntity<String> getConsumption(@RequestParam("meter_number") String meterNumber)
			throws MeterControllerException {
		try {
			Meter meter = service.findById(meterNumber);
			if (meter == null) {
				return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(String.valueOf(meter.getConsumption()), HttpStatus.OK);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "", exc);
		}

	}

	@GetMapping(value = "/microgeneration")
	public ResponseEntity<String> getMicroGeneration(@RequestParam("meter_number") String meterNumber)
			throws MeterControllerException {		
		try {
			Meter meter = service.findById(meterNumber);
			if (meter == null) {
				return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(String.valueOf(meter.getMicrogeneration()), HttpStatus.OK);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "", exc);
		}		
	}

}