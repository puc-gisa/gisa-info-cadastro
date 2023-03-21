package br.com.gisa.gisainfocadastro.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public FanoutExchange createAssociadoExchange() {
        return new FanoutExchange(Amqp.NOVO_ASSOCIADO_EXCHANGE);
    }

    @Bean
    public FanoutExchange updateAssociadoExchange() {
        return new FanoutExchange(Amqp.ATUALIZADO_ASSOCIADO_EXCHANGE);
    }

}
