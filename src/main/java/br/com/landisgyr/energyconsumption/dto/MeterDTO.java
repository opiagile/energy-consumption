package br.com.landisgyr.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class MeterDTO {
	private String meterNumber;
	private String type;
	private Double unit;
	private Long activeEnergy;
	private Long injectedEnergy;

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getUnit() {
		return unit;
	}

	public void setUnit(Double unit) {
		this.unit = unit;
	}

	public Long getActiveEnergy() {
		return activeEnergy;
	}

	public void setActiveEnergy(Long activeEnergy) {
		this.activeEnergy = activeEnergy;
	}

	public Long getInjectedEnergy() {
		return injectedEnergy;
	}

	public void setInjectedEnergy(Long injectedEnergy) {
		this.injectedEnergy = injectedEnergy;
	}
}