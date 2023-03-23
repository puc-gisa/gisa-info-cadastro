package br.com.gisa.gisainfocadastro.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public FanoutExchange createAssociadoExchange() {
        return new FanoutExchange(Amqp.ASSOCIADO_NOVO_EXCHANGE);
    }

    @Bean
    public FanoutExchange updateAssociadoExchange() {
        return new FanoutExchange(Amqp.ASSOCIADO_ATUALIZADO_EXCHANGE);
    }

    @Bean
    public FanoutExchange requestAutorizacaoExameExchange() {
        return new FanoutExchange(Amqp.SOLICITACAO_AUTORIZACAO_EXAME_EXCHANGE);
    }

    @Bean
    public FanoutExchange responseAutorizacaoExameExchange() {
        return new FanoutExchange(Amqp.RESPOSTA_AUTORIZACAO_EXAME_EXCHANGE);
    }

    @Bean
    public Queue queueResponseAutorizacaoExame() {
        return new Queue(Amqp.RESPOSTA_AUTORIZACAO_EXAME_QUEUE);
    }

    @Bean
    public Binding bindingResponseAutorizacaoExame() {
        return BindingBuilder.bind(queueResponseAutorizacaoExame()).to(responseAutorizacaoExameExchange());
    }

}
