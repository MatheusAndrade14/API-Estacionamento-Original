package br.com.estacionamento.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class HistoricoRegistroEstacionamento {

	private Long id;
	private boolean paid;
	private boolean exit;
	private String minutes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	
	public static HistoricoRegistroEstacionamento registroEstacionamentoToHistoricoRegistroEstacionamento(RegistroEstacionamento registroEstacionamento) {
		
		HistoricoRegistroEstacionamento historicoRegistroEstacionamento = new HistoricoRegistroEstacionamento();
		historicoRegistroEstacionamento.setId(registroEstacionamento.getIdRegistro());
		historicoRegistroEstacionamento.setPaid(registroEstacionamento.isPagamento());
		historicoRegistroEstacionamento.setExit(registroEstacionamento.isSaida());
		
		LocalDateTime horaSaidaOuAtual = registroEstacionamento.getDataHoraSaida() != null ? registroEstacionamento.getDataHoraSaida() : LocalDateTime.now() ;
		
		Duration duration = Duration.between(registroEstacionamento.getDataHoraEntrada(), horaSaidaOuAtual);
		int minutes = (int) ((duration.getSeconds() % (60 * 60)) / 60);
		
		historicoRegistroEstacionamento.setMinutes(String.valueOf( minutes + " minutes"));
		
		return historicoRegistroEstacionamento;
	}
	
}

