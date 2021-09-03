package br.com.landisgyr.energyconsumption.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import br.com.landisgyr.energyconsumption.model.Meter;

@JsonInclude(Include.NON_NULL)
public class MeterVO extends Meter {

	public MeterVO(Meter meter) {
		super();
		if (meter != null) {
			setMeterNo(meter.getMeterNo());
			setConsumption(meter.getConsumption());
			setMicrogeneration(meter.getMicrogeneration());
			setCash(meter.getCash());
		}
	}

	public MeterVO(String meterNo, Double cash) {
		super(meterNo, cash);
	}

	public MeterVO(String meterNo, Long consumption, Long microgeneration) {
		super(meterNo, consumption, microgeneration);
	}

}
