package com.simulador.entidade;

import java.util.ArrayList;
import java.util.List;

public class ResultadoSimulacao {
	
	private String tipo;
	private List<Parcela> parcelas = new ArrayList();
	
	public ResultadoSimulacao(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void adicionarParcela(Parcela parcela) {
		parcelas.add(parcela);
	}
	
	public List<Parcela> getParcelas() {
		
		return parcelas;	
	}
}
