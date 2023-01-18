package br.com.estacionamento.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estacionamento.config.MessageException;
import br.com.estacionamento.model.HistoricoRegistroEstacionamento;
import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.repository.EstacionamentoRepository;
import br.com.estacionamento.service.EstacionamentoService;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

	@Autowired
	private EstacionamentoRepository estacionamentoRepository;

	@Override
	public String salvarRegistroEstacionamento(RegistroEstacionamento registroEstacionamento) throws Exception {

		List<RegistroEstacionamento> listRegistroEstacionamento = estacionamentoRepository
				.findByPlacaAtiva(registroEstacionamento.getPlaca());

		validarRegistroEmAbertoParaPlacaVeiculo(listRegistroEstacionamento);

		estacionamentoRepository.save(registroEstacionamento);

		return registroEstacionamento.getCodigoReserva();

	}

	private void validarRegistroEmAbertoParaPlacaVeiculo(List<RegistroEstacionamento> listRegistroEstacionamento)
			throws Exception {

		if (listRegistroEstacionamento.size() > 0) {
			throw new Exception(MessageException.REGISTRO_EXISTENTE);
		}
	}

	@Override
	public void registrarSaida(String placa) throws Exception {

		List<RegistroEstacionamento> listRegistroEstacionamento = estacionamentoRepository.findByPlacaAtivaEPaga(placa);

		validarRegistroAbertoEPago(listRegistroEstacionamento);

		estacionamentoRepository.registrarSaida(placa, LocalDateTime.now());

	}

	private void validarRegistroAbertoEPago(List<RegistroEstacionamento> registroEstacionamentoList) throws Exception {
		if (!(registroEstacionamentoList.size() > 0)) {
			throw new Exception(MessageException.REGISTRO_PAGO_NAO_EXISTENTE);

		}
	}

	@Override
	public void registrarPagamento(String placa) throws Exception {

		List<RegistroEstacionamento> listRegistroEstacionamento = estacionamentoRepository.findByPlacaAtiva(placa);

		validarRegistroExistenteAberto(listRegistroEstacionamento);

		estacionamentoRepository.registrarPagamento(placa);
	}

	private void validarRegistroExistenteAberto(List<RegistroEstacionamento> registroEstacionamentoList)
			throws Exception {
		if (registroEstacionamentoList.size() == 0) {
			throw new Exception(MessageException.REGISTRO_ABERTO_NAO_EXISTENTE);
		}
	}

	@Override
	public List<HistoricoRegistroEstacionamento> recuperarTodosRegistrosPlaca(String placa) {

		List<RegistroEstacionamento> listRegistroEstacionamento = estacionamentoRepository.recuperarTodosRegistrosPlaca(placa);
		
		List<HistoricoRegistroEstacionamento> listHistoricoRegistroEstacionamento = convertListRegistroEstacionamentoToListHistoricoRegistroEstacionamento(
				listRegistroEstacionamento);

		return listHistoricoRegistroEstacionamento;

	}

	private List<HistoricoRegistroEstacionamento> convertListRegistroEstacionamentoToListHistoricoRegistroEstacionamento(
			List<RegistroEstacionamento> registroEstacionamentoList) {
		List<HistoricoRegistroEstacionamento> listHistoricoRegistroEstacionamento = new ArrayList<>();

		registroEstacionamentoList.stream().forEach(registroEstacionamento -> {
			
			HistoricoRegistroEstacionamento historicoRegistroEstacionamento = HistoricoRegistroEstacionamento
					.registroEstacionamentoToHistoricoRegistroEstacionamento(registroEstacionamento);
			
			listHistoricoRegistroEstacionamento.add(historicoRegistroEstacionamento);
		});
		return listHistoricoRegistroEstacionamento;
	}

}