package com.simulador.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	@Column(name="CO_PRODUTO")
	private Long codigo;
	
	@Column(name="NO_PRODUTO")
	private String nome;
	
	@Column(name="PC_TAXA_JUROS")
	private double taxa;
	
	@Column(name="NU_MINIMO_MESES")
	private int minimoMeses;
	
	@Column(name="NU_MAXIMO_MESES")
	private Integer maximoMeses;
	
	@Column(name="VR_MINIMO")
	private Double valorMinimo;
	
	@Column(name="VR_MAXIMO")
	private Double valorMaximo;

	public Long getCodigo(){
		return codigo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public double getTaxa(){
		return taxa;
	}
	
	public int getMinimoMeses(){
		return minimoMeses;
	}
	
	public Integer getMaximoMeses(){
		
		/*caso prazo maximo esteja vazio, retornara o mesmo prazo minimo
		 *prazo da operacao sendo apenas o definido como minimo
		 */
		if (maximoMeses == null) {
			return minimoMeses;
		}
		
		return maximoMeses;
	}
	
	public double getValorMinimo() {
		return valorMinimo;
	}
	
	public Double getValorMaximo(){
		/*caso vlr maximo esteja vazio, retornara o mesmo vlr minimo
		 *valor da operacao sendo apenas o definido como minimo
		 */
		if (maximoMeses == null) {
			return valorMinimo;
		}
		
		return valorMaximo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public void setMinimoMeses(int minimoMeses) {
		this.minimoMeses = minimoMeses;
	}

	public void setMaximoMeses(Integer maximoMeses) {
		this.maximoMeses = maximoMeses;
	}

	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public void setValorMaximo(Double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
}
