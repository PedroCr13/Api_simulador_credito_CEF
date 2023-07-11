package com.simulador.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Simulacao {

		private long codigoProduto;
		private String descricaoProduto;
		private double taxaJuros;
		private List<ResultadoSimulacao> resultadoSimulacao;
		
		public Simulacao() {
			resultadoSimulacao = new ArrayList();
		}
		
		public long getCodigoProduto() {
			return codigoProduto;
		}
		public void setCodigoProduto(long codigoProduto) {
			this.codigoProduto = codigoProduto;
		}
		public String getDescricaoProduto() {
			return descricaoProduto;
		}
		public void setDescricaoProduto(String descricaoProduto) {
			this.descricaoProduto = descricaoProduto;
		}
		public double getTaxaJuros() {
			return taxaJuros;
		}
		public void setTaxaJuros(double taxaJuros) {
			this.taxaJuros = taxaJuros;
		}
	
		public void setPrestacoes(ResultadoSimulacao resultado) {
			this.resultadoSimulacao.add(resultado);
		}
		
		public List<ResultadoSimulacao> getResultadoSimulacao() {
			return resultadoSimulacao;
		}
		
		public void calcularSac(double valorSolicitado, int prazoSolicitado) {

			double amortizacao = valorSolicitado / prazoSolicitado; 
			double saldoDevedor = valorSolicitado;
			
			ResultadoSimulacao prestacoesDoCredito = new ResultadoSimulacao("SAC");

			for(int i = 0; i < prazoSolicitado; i++) {
				Parcela parcela = new Parcela();
				
				double valorDosJuros = saldoDevedor * taxaJuros;
				
				parcela.setNumero(i+1); 	
				parcela.setValorAmortizacao(arredondarrDuasCasas(amortizacao));
				parcela.setValorJuros(arredondarrDuasCasas(valorDosJuros));
				parcela.setValorPrestacao(arredondarrDuasCasas(amortizacao + valorDosJuros));
				prestacoesDoCredito.adicionarParcela(parcela);

				saldoDevedor = saldoDevedor - amortizacao;
			}
			
			resultadoSimulacao.add(prestacoesDoCredito);			
		}
		
		public void calcularPrice(double valorSolicitado, int prazoSolicitado) {
			
			double saldoDevedor = valorSolicitado;
			double calculoAmortizacao = 0;
			double calculaFator = 0;
			double prestacao = 0;
			int n = prazoSolicitado;
			
			ResultadoSimulacao prestacoesDoCredito = new ResultadoSimulacao("Price");
			
			for (int i = 0; i < prazoSolicitado; i++){
				Parcela parcela = new Parcela();
				double valorJuros = saldoDevedor * taxaJuros;
				
				calculaFator = Math.pow(1 + taxaJuros, n);			
				calculoAmortizacao = (saldoDevedor * taxaJuros) / (calculaFator - 1);
				prestacao = calculoAmortizacao + valorJuros;
				saldoDevedor = saldoDevedor - calculoAmortizacao;
				
				parcela.setNumero(i+1);
				parcela.setValorAmortizacao(arredondarrDuasCasas(calculoAmortizacao));
				parcela.setValorJuros(arredondarrDuasCasas(valorJuros));
				parcela.setValorPrestacao(arredondarrDuasCasas(calculoAmortizacao + valorJuros));
				prestacoesDoCredito.adicionarParcela(parcela);
				n--;
			}		
			
			resultadoSimulacao.add(prestacoesDoCredito);
		}
		
		private double arredondarrDuasCasas(double valor) {

			BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
			
			return bd.doubleValue();
		};
}
