package br.com.gisa.gisainfocadastro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "endereco")
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_associado")
    @ToString.Exclude
    private AssociadoEntity associado;

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
