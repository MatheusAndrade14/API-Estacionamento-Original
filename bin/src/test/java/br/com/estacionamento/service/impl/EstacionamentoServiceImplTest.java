package br.com.estacionamento.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.repository.EstacionamentoRepository;
import br.com.estacionamento.service.impl.EstacionamentoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EstacionamentoServiceImplTest {

	@InjectMocks
	private EstacionamentoServiceImpl estacionamentoServiceImpl;

	@Mock
	private EstacionamentoRepository estacionamentoRepository;

	RegistroEstacionamento registroEstacionamento = new RegistroEstacionamento();

	List<RegistroEstacionamento> listRegistroEstacionamento = new ArrayList<>();

	List<RegistroEstacionamento> listRegistroEstacionamentoVazia = new ArrayList<>();

	@Before
	public void setup() {
		registroEstacionamento.setPlaca("AAA-9999");

		listRegistroEstacionamento.add(registroEstacionamento);
	}

	@Test
	public void deveSalvarRegistroEstacionamento() throws Exception {

		estacionamentoServiceImpl.salvarRegistroEstacionamento(registroEstacionamento);

		verify(estacionamentoRepository, times(1)).save(registroEstacionamento);
	}

	@Test(expected = Exception.class)
	public void deveLançarExceptionAoTentarSalvarRegistroEstacionamento() throws Exception {
		when(estacionamentoRepository.findByPlacaAtiva(anyString())).thenReturn(listRegistroEstacionamento);

		estacionamentoServiceImpl.salvarRegistroEstacionamento(registroEstacionamento);
	}

	@Test
	public void deveRegistrarPagamento() throws Exception {
		when(estacionamentoRepository.findByPlacaAtiva(anyString())).thenReturn(listRegistroEstacionamento);

		estacionamentoServiceImpl.registrarPagamento("AAA-1234");

		verify(estacionamentoRepository, times(1)).registrarPagamento(anyString());
		verify(estacionamentoRepository, times(1)).findByPlacaAtiva(anyString());
	}

	@Test(expected = Exception.class)
	public void deveLançarExceptionAoTentarRegistrarPagamento() throws Exception {
		when(estacionamentoRepository.findByPlacaAtiva(anyString())).thenReturn(listRegistroEstacionamentoVazia);

		estacionamentoServiceImpl.registrarPagamento("AAA-1234");
	}

	@Test
	public void deveRegistrarSaida() throws Exception {
		when(estacionamentoRepository.findByPlacaAtivaEPaga(anyString())).thenReturn(listRegistroEstacionamento);

		estacionamentoServiceImpl.registrarSaida("AAA-1234");

		verify(estacionamentoRepository, times(1)).registrarSaida(anyString(), any());
		verify(estacionamentoRepository, times(1)).findByPlacaAtivaEPaga(anyString());
	}

	@Test(expected = Exception.class)
	public void deveLançarExceptionAoTentardeveRegistrarSaida() throws Exception {
		when(estacionamentoRepository.findByPlacaAtivaEPaga(anyString())).thenReturn(listRegistroEstacionamentoVazia);

		estacionamentoServiceImpl.registrarSaida("AAA-1234");
	}

	@Test
	public void deveReuperarTodosRegistrosPlaca() throws Exception {

		estacionamentoServiceImpl.recuperarTodosRegistrosPlaca("AAA-1234");

		verify(estacionamentoRepository, times(1)).recuperarTodosRegistrosPlaca(anyString());
	}

}