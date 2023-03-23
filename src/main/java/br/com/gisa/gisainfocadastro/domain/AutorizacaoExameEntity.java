package br.com.gisa.gisainfocadastro.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "autorizacao_exame")
@NoArgsConstructor
public class AutorizacaoExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idAssociado;

    @Column(length = 20)
    private String codigoExame;

    @Column
    private LocalDate dataExame;

    @Column(length = 20)
    private String crmMedicoSolicitante;

    @Column
    private LocalDateTime dataSolicitacao;

    @Column
    private Integer codigoSituacao;

    @Column
    private LocalDate dataValidade;

    @Column
    private String justificativa;
}
