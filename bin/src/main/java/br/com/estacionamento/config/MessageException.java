package br.com.estacionamento.config;

public class MessageException {
	
	public static final String REGISTRO_EXISTENTE = "Já existe um registro em aberto para está placa";

	public static final String REGISTRO_PAGO_NAO_EXISTENTE = "Não foi possivel registrar a saída. Nenhum registro pago encontrado para está placa";

	public static final String REGISTRO_ABERTO_NAO_EXISTENTE = "Não foi possivel registrar o pagamento. Não existe nenhum registro em aberto para está placa";

}
