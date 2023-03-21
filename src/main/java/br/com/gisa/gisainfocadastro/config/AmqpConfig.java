package br.com.gisa.gisainfocadastro.config;

import org.springframework.amqp.core.FanoutExchange;
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

}
