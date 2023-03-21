package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.domain.TipoPlano;
import br.com.gisa.gisainfocadastro.service.TipoPlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo-plano/")
@RequiredArgsConstructor
public class TipoPlanoSaudeController {

    private final TipoPlanoService service;

    @GetMapping("/")
    public List<TipoPlano> getAll() {
        return service.findAll();
    }

    @GetMapping("/{tipoCliente}")
    public List<TipoPlano> getById(@PathVariable Integer tipoCliente) {
        return service.findByTipoCliente(tipoCliente);
    }

}
