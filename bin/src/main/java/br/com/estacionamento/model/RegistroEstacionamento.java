package br.com.estacionamento.model;

import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class RegistroEstacionamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long idRegistro;
	@JsonProperty("plate")
	@Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "O valor informado deve estar no padrão: AAA-9999")
	private String placa;
	private boolean pagamento;
	private boolean saida;
	private LocalDateTime dataHoraEntrada;
	private LocalDateTime dataHoraSaida;
	private String codigoReserva;

	public RegistroEstacionamento() {
		this.dataHoraEntrada = LocalDateTime.now();
		this.codigoReserva = gerarCodigoReserva();
	}

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isPagamento() {
		return pagamento;
	}

	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}

	public boolean isSaida() {
		return saida;
	}

	public void setSaida(boolean saida) {
		this.saida = saida;
	}

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	private String gerarCodigoReserva() {
		StringBuilder codigoReserva = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			codigoReserva.append(random.nextInt(10));
		}
		return codigoReserva.toString();
	}

}
