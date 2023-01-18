package br.com.estacionamento.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.estacionamento.model.HistoricoRegistroEstacionamento;
import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.service.EstacionamentoService;

@RunWith(MockitoJUnitRunner.class)
public class EstacionamentoControllerTest {

	@InjectMocks
	EstacionamentoController controller;
	
	@Mock
	EstacionamentoService estacionamentoService;
	
	RegistroEstacionamento registroEstacionamento = new RegistroEstacionamento();
	
	HistoricoRegistroEstacionamento historicoRegistroEstacionamento = new HistoricoRegistroEstacionamento();
	
	List<HistoricoRegistroEstacionamento> listRegistroEstacionamento = new ArrayList<>();


	@Before
	public void setup() {
		registroEstacionamento.setPlaca("AAA-9999");
		historicoRegistroEstacionamento.setId(Long.valueOf(1));
		historicoRegistroEstacionamento.setMinutes("30 minutes");
		listRegistroEstacionamento.add(historicoRegistroEstacionamento);
	}
	
	@Test
	public void deveSalvarRegistroEstacionamento() throws Exception {
		when(estacionamentoService.salvarRegistroEstacionamento(any())).thenReturn("123456");
	
		ResponseEntity<Map<String, String>> map = controller.salvarRegistroEstacionamento(registroEstacionamento);
		
		assertEquals(201, map.getStatusCode().value());
	}
	
	@Test
	public void deveRetornarMensagemErroAoSalvarRegistroEstacionamento() throws Exception {
		when(estacionamentoService.salvarRegistroEstacionamento(any())).thenThrow(Exception.class);
	
		ResponseEntity<Map<String, String>> map = controller.salvarRegistroEstacionamento(registroEstacionamento);
		
		assertTrue(map.getBody().containsKey("message"));
		assertEquals(400, map.getStatusCode().value());
		
	}
	
	@Test
	public void deveRegistrarSaida() throws Exception {
	
		ResponseEntity<Map<String, String>> map = controller.registrarSaida("AAA-1234");
		
		verify(estacionamentoService, times(1)).registrarSaida(anyString());
		assertEquals(200, map.getStatusCode().value());

	}
	
	@Test
	public void deveRetornarMensagemErroAoRegistrarSaida() throws Exception {
		
		doThrow(Exception.class).when(estacionamentoService).registrarSaida(any());

	
		ResponseEntity<Map<String, String>> map = controller.registrarSaida("AAA-1234");
		
		assertTrue(map.getBody().containsKey("message"));
		assertEquals(400, map.getStatusCode().value());

	}
	
	@Test
	public void deveRegistrarPagamento() throws Exception {
	
		ResponseEntity<Map<String, String>> map = controller.registrarPagamento("AAA-1234");
		
		verify(estacionamentoService, times(1)).registrarPagamento(anyString());
		assertEquals(200, map.getStatusCode().value());

	}
	
	@Test
	public void deveRetornarMensagemErroAoRegistrarPagamento() throws Exception {
		
		doThrow(Exception.class).when(estacionamentoService).registrarPagamento(any());

	
		ResponseEntity<Map<String, String>> map = controller.registrarPagamento("AAA-1234");
		
		assertTrue(map.getBody().containsKey("message"));
		assertEquals(400, map.getStatusCode().value());

	}
	
	@Test
	public void deveRecuperarTodosRegistrosPlaca() throws Exception {
		
		when(estacionamentoService.recuperarTodosRegistrosPlaca(any())).thenReturn(listRegistroEstacionamento);

	
		ResponseEntity<Map<String, List<HistoricoRegistroEstacionamento>>> map = controller.recuperarTodosRegistrosPlaca("AAA-1234");
		
		verify(estacionamentoService, times(1)).recuperarTodosRegistrosPlaca(anyString());
		assertEquals(200, map.getStatusCode().value());

	}
	
}
