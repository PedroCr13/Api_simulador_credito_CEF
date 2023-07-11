package com.simulador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.messaging.eventhubs.EventData;
import com.google.gson.Gson;
import com.simulador.database.RepositorioProduto;
import com.simulador.entidade.Produto;
import com.simulador.entidade.Simulacao;
import com.simulador.entidade.SolicitacaoCliente;

import azure.eventhub.Sender;


@RestController
@RequestMapping(value = "/")
public class SimulacaoRest {

	@Autowired
	private RepositorioProduto repositorio;	
	
	@PostMapping(value = "/simular")
	public ResponseEntity<?> simular(@RequestBody @Validated SolicitacaoCliente solicitacao){
			
		List<Produto> produtosLocalizados = repositorio.findByProdutoAdequado(solicitacao.getValor());
		
		if (produtosLocalizados.isEmpty()){
			return ResponseEntity.badRequest().body("Não há produtos disponiveis");
		}
		
		Produto p = new Produto();		
		p.setCodigo(produtosLocalizados.get(0).getCodigo());
		p.setNome(produtosLocalizados.get(0).getNome());
		p.setTaxa(produtosLocalizados.get(0).getTaxa());
		p.setMinimoMeses(produtosLocalizados.get(0).getMinimoMeses());
		p.setMaximoMeses(produtosLocalizados.get(0).getMaximoMeses());
		p.setValorMinimo(produtosLocalizados.get(0).getValorMinimo());
		p.setValorMaximo(produtosLocalizados.get(0).getValorMaximo());
		
		if(solicitacao.getValor() > p.getValorMaximo()){
			return ResponseEntity.badRequest().body("Valor não disponivel ");
		} else		
		if(solicitacao.getPrazo() < p.getMinimoMeses()){
			return ResponseEntity.badRequest().body("Prazo minimo é " + p.getMinimoMeses() +" meses");
			 	  
		} else 
		if (solicitacao.getPrazo() > p.getMaximoMeses()) {
			return ResponseEntity.badRequest().body("Prazo máximo é " + p.getMaximoMeses() + " meses");
		}			
		else {			
			Simulacao simulacaoCliente = new Simulacao();
			simulacaoCliente.setCodigoProduto(p.getCodigo());
			simulacaoCliente.setDescricaoProduto(p.getNome());
			simulacaoCliente.setTaxaJuros(p.getTaxa());
			simulacaoCliente.calcularSac(solicitacao.getValor(), solicitacao.getPrazo());
			simulacaoCliente.calcularPrice(solicitacao.getValor(), solicitacao.getPrazo());
		
			Sender enviaSimulacaoParaEventHub = new Sender(); 
			//enviaSimulacaoParaEventHub.publishEvents(simulacaoCliente);

			return new ResponseEntity<Simulacao>(simulacaoCliente, HttpStatus.OK);
		}
	}
}
