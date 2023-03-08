package br.com.gisa.gisainfocadastro.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "plano_saude")
@NoArgsConstructor
public class PlanoSaudeIndividualEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idAssociado;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column
    private Integer codigoTipoPlano;

    public boolean isAtivo() {
        LocalDate today = LocalDate.now();
        return (dataInicio.isBefore(today) || dataInicio.isEqual(today))
            && (isNull(dataFim) || dataFim.isEqual(today) || dataFim.isAfter(today));
    }
}
