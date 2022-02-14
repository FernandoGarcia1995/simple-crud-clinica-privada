package com.clinicadental.clinicadental.response.object;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Facturacion {
	
	private BigDecimal gastosTotales;
	private BigDecimal gastosMensuales;
	private BigDecimal IngresosTotales;
	private Map<String, BigDecimal> IngresosMensualesPorMes;
	

	public Facturacion() {
	}

	public Facturacion(BigDecimal gastosTotales, BigDecimal gastosMensuales, BigDecimal ingresosTotales,
			Map<String, BigDecimal> IngresosMensualesPorMes) {
		this.gastosTotales = gastosTotales;
		this.gastosMensuales = gastosMensuales;
		IngresosTotales = ingresosTotales;
		IngresosMensualesPorMes = IngresosMensualesPorMes;
	}

	public BigDecimal getGastosTotales() {
		return gastosTotales;
	}

	public void setGastosTotales(BigDecimal gastosTotales) {
		this.gastosTotales = gastosTotales;
	}

	public BigDecimal getGastosMensuales() {
		return gastosMensuales;
	}

	public void setGastosMensuales(BigDecimal gastosMensuales) {
		this.gastosMensuales = gastosMensuales;
	}

	public BigDecimal getIngresosTotales() {
		return IngresosTotales;
	}

	public void setIngresosTotales(BigDecimal ingresosTotales) {
		IngresosTotales = ingresosTotales;
	}

	public Map<String, BigDecimal> getIngresosMensualesPorMes() {
		return IngresosMensualesPorMes;
	}

	public void setIngresosMensualesPorMes(Map<String, BigDecimal> ingresosMensualesPorMes) {
		IngresosMensualesPorMes = ingresosMensualesPorMes;
	}
	
}
