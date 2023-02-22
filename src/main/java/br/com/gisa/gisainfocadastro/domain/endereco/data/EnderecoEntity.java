package br.com.gisa.gisainfocadastro.domain.endereco.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "endereco")
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idAssociado;

    @Column(length = 255)
    private String logradouro;

    @Column
    private Integer numero;

    @Column(length = 255)
    private String complemento;

    @Column(length = 255)
    private String bairro;

    @Column(length = 255)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(length = 8)
    private String cep;

}
