package com.simulador.entidade;

import com.sun.istack.NotNull;

public class SolicitacaoCliente {
	
	@NotNull
	public double valor;
	
	@NotNull
	public int prazo;
	
	public SolicitacaoCliente(){
		double valor = 0;
		int prazo = 0;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

}
