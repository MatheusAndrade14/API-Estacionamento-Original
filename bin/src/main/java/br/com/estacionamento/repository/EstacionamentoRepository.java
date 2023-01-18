package br.com.estacionamento.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.model.RegistroEstacionamento;
@Repository
public interface EstacionamentoRepository extends JpaRepository<RegistroEstacionamento, Long> {

	@Query(value = "SELECT * FROM REGISTRO_ESTACIONAMENTO WHERE PLACA = ?1 AND SAIDA = FALSE", nativeQuery = true)
	List<RegistroEstacionamento> findByPlacaAtiva(String placa);
	
	@Query(value = "SELECT * FROM REGISTRO_ESTACIONAMENTO WHERE PLACA = ?1 AND SAIDA = FALSE AND PAGAMENTO = TRUE", nativeQuery = true)
	List<RegistroEstacionamento> findByPlacaAtivaEPaga(String placa);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE REGISTRO_ESTACIONAMENTO SET SAIDA = TRUE, DATA_HORA_SAIDA = ?2 WHERE PLACA = ?1 AND SAIDA = FALSE", nativeQuery = true)
	void registrarSaida(String placa, LocalDateTime dataHoraSaida);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE REGISTRO_ESTACIONAMENTO SET PAGAMENTO = TRUE WHERE PLACA = ?1 ", nativeQuery = true)
	void registrarPagamento(String placa);
	
	@Query(value = "SELECT * FROM REGISTRO_ESTACIONAMENTO WHERE PLACA = ?1", nativeQuery = true)
	List<RegistroEstacionamento> recuperarTodosRegistrosPlaca(String placa);
	
}