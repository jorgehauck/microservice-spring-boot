package br.com.alura.microservice.lojafloricultura.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.alura.microservice.lojafloricultura.client.FornecedorClient;
import br.com.alura.microservice.lojafloricultura.dto.CompraDTO;
import br.com.alura.microservice.lojafloricultura.dto.InfoFornecedorDTO;
import br.com.alura.microservice.lojafloricultura.dto.InfoPedidoDTO;
import br.com.alura.microservice.lojafloricultura.model.Compra;
import br.com.alura.microservice.lojafloricultura.repository.CompraRepository;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@HystrixCommand
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback")
	public Compra realizaCompra(CompraDTO compra) {
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
	
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		final String estado = compra.getEndereco().getEstado();
		LOG.info("buscando informações do forncedor de {}", estado);
		
		LOG.info("realizando um pedido");
		
		System.out.println(info.getEndereco());
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		compraRepository.save(compraSalva);
		
		return compraSalva;
	}
	
	public Compra realizaCompraFallback(CompraDTO compra) {
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallback;
	}
}

