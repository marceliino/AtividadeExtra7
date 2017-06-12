package br.com.fiap.aplicacao;

import java.util.Date;
import java.util.List;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;

public class Main {

	public static void main(String[] marcelo) {
		
		cadastrarCliente();
		Cliente cliente = listarCliente();
		Cliente clienteBusca = buscandoCliente(cliente.getId());
		atualizandoCliente(clienteBusca);
		removendoCliente(clienteBusca);
	}

	private static void removendoCliente(Cliente cliente) {
		Dao<Cliente> dao = new GenericDao<Cliente>(Cliente.class);
		System.out.println("################### REMOVENDO CLIENTE ###################");
		System.out.println("Cliente: " + cliente.getNome() + " - " + cliente.getEmail());
		System.out.println("#########################################################");
		dao.remover(cliente);
		System.out.println("");
	}

	private static Cliente buscandoCliente(int id) {
		Dao<Cliente> dao = new GenericDao<Cliente>(Cliente.class);
		Cliente cliente = dao.buscar(id);
		System.out.println("################### BUSCANDO CLIENTE ###################");
		System.out.println("Cliente: " + cliente.getNome() + " - " + cliente.getEmail());
		System.out.println("#########################################################");
		System.out.println("");
		return cliente;
	}

	private static void atualizandoCliente(Cliente cliente) {
		Dao<Cliente> dao = new GenericDao<Cliente>(Cliente.class);
		cliente.setNome("Marcelo SiLva " + Math.random());
		dao.atualizar(cliente);
		Cliente clienteAlt = dao.buscar(cliente.getId());
		System.out.println("################### ATUALIZADO CLIENTE ###################");
		System.out.println("Cliente: " + clienteAlt.getNome() + " - " + clienteAlt.getEmail());
		System.out.println("#########################################################");
		System.out.println("");
	}

	private static Cliente listarCliente() {
		
		Dao<Cliente> dao = new GenericDao<Cliente>(Cliente.class);
		List<Cliente> clientes = dao.listar();
		
		for (Cliente cliente : clientes) {
			System.out.println("################### LISTANDO CLIENTE ###################");
			System.out.println("> Cliente: " + cliente.getId() + " - " + cliente.getNome() + " - " + cliente.getEmail());
			
			System.out.println(">> Pedidos:");
			for(Pedido pedido : cliente.getPedidos()) {
				System.out.println(">>> " + pedido.getDescricao() + " - " + pedido.getValor());
			}
		}
		System.out.println("#########################################################");
		System.out.println("");
		return clientes.get(0);
	}

	private static void cadastrarCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcelo SiLva");
		cliente.setEmail("marceliino1@gmail.com");
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(new Date());
		pedido.setDescricao("Notebook Dell");
		pedido.setValor(4960.0d);
		cliente.getPedidos().add(pedido);
		
		Pedido pedido2 = new Pedido();
		pedido2.setCliente(cliente);
		pedido2.setData(new Date());
		pedido2.setDescricao("Monitor Samsung");
		pedido2.setValor(2951.36d);	
		cliente.getPedidos().add(pedido2);
		Dao<Cliente> dao = new GenericDao<Cliente>(Cliente.class);
		dao.adicionar(cliente);
	}	
}