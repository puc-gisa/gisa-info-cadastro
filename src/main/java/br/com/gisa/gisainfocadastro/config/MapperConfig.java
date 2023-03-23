package br.com.gisa.gisainfocadastro.config;

import br.com.gisa.gisainfocadastro.converter.AutorizacaoExameResponseConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    @Qualifier
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(new AutorizacaoExameResponseConverter());
        return modelMapper;
    }
}
