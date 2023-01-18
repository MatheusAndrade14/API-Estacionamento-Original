package br.com.estacionamento.service;

import java.util.List;

import br.com.estacionamento.model.HistoricoRegistroEstacionamento;
import br.com.estacionamento.model.RegistroEstacionamento;

public interface EstacionamentoService {


	String salvarRegistroEstacionamento(RegistroEstacionamento registroEstacionamento) throws Exception;

	void registrarSaida(String placa) throws Exception;

	void registrarPagamento(String placa) throws Exception;

	public List<HistoricoRegistroEstacionamento> recuperarTodosRegistrosPlaca(String placa);
}
