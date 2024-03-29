package br.com.gisa.gisainfocadastro.config;

public class Amqp {

    private Amqp() {
    }

    public static final String ASSOCIADO_NOVO_EXCHANGE = "associado.novo";
    public static final String ASSOCIADO_ATUALIZADO_EXCHANGE = "associado.atualizado";

    public static final String SOLICITACAO_AUTORIZACAO_EXAME_EXCHANGE = "solicitacao.autorizacao.exame";
    public static final String RESPOSTA_AUTORIZACAO_EXAME_EXCHANGE = "resposta.autorizacao.exame";

    public static final String RESPOSTA_AUTORIZACAO_EXAME_QUEUE = "info.cadastro.resposta.autorizacao.exame";
}
