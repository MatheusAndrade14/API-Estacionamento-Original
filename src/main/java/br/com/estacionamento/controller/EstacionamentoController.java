package br.com.estacionamento.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estacionamento.model.HistoricoRegistroEstacionamento;
import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.service.EstacionamentoService;

@RestController
@RequestMapping("/v1/parking")
public class EstacionamentoController {

	@Autowired
	private EstacionamentoService estacionamentoService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Map<String,String>> salvarRegistroEstacionamento(
			@RequestBody @Valid RegistroEstacionamento registroEstacionamento) {

		String codigoReserva = "";
		final Map<String, String> retorno = new HashMap<>();

		try {
			codigoReserva = estacionamentoService.salvarRegistroEstacionamento(registroEstacionamento);

			retorno.put("codigo_reserva", codigoReserva);

			return ResponseEntity.created(null).body(retorno);
		} catch (Exception e) {
			retorno.put("message", e.getMessage());

			return ResponseEntity.badRequest().body(retorno);
		}

	}

	@PutMapping(path = "/{plate}/out", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Map<String, String>> registrarSaida(@PathVariable String plate) {

		final Map<String, String> retorno = new HashMap<>();

		try {
			estacionamentoService.registrarSaida(plate);
			retorno.put("message", "Saída registrada com sucesso para a placa: " + plate);

			return ResponseEntity.ok().body(retorno);
		} catch (Exception e) {
			retorno.put("message", e.getMessage());

			return ResponseEntity.badRequest().body(retorno);
		}

	}

	@PutMapping(path = "/{plate}/pay", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Map<String, String>> registrarPagamento(@PathVariable String plate) {

		final Map<String, String> retorno = new HashMap<>();

		try {
			estacionamentoService.registrarPagamento(plate);
			retorno.put("message", "Pagamento registrado com sucesso para a placa: " + plate);

			return ResponseEntity.ok().body(retorno);
		} catch (Exception e) {
			retorno.put("message", e.getMessage());

			return ResponseEntity.badRequest().body(retorno);
		}

	}

	@GetMapping(path = "/{plate}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Map<String, List<HistoricoRegistroEstacionamento>>> recuperarTodosRegistrosPlaca(@PathVariable String plate) {

		final Map<String, List<HistoricoRegistroEstacionamento>> retorno = new HashMap<>();

		List<HistoricoRegistroEstacionamento> listRegistroEstacionamento = estacionamentoService
				.recuperarTodosRegistrosPlaca(plate);
		

		retorno.put("data", listRegistroEstacionamento);

		return ResponseEntity.ok().body(retorno);
	}

}
