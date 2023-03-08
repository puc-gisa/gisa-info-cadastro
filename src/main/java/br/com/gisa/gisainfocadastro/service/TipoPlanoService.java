package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.domain.TipoCliente;
import br.com.gisa.gisainfocadastro.domain.TipoPlano;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TipoPlanoService {

    public List<TipoPlano> findAll() {
        return List.of(TipoPlano.values());
    }

    public List<TipoPlano> findByTipoCliente(Integer coditoTipoCliente) {
        Optional<TipoCliente> tipoCliente = TipoCliente.ofCodigo(coditoTipoCliente);
        return TipoPlano.ofTipoCliente(tipoCliente.orElseThrow(NotFoundException::new));
    }

    public Optional<TipoPlano> findByCodigoTipoPlano(Integer codigoTipoPlano) {
        return TipoPlano.ofCodigo(codigoTipoPlano);
    }
}
