package br.inf.microsys.treinamentojava.model;

import br.inf.microsys.treinamentojava.annotations.IdadeMinima;
import br.inf.microsys.treinamentojava.annotations.IniciarComLetra;
import br.inf.microsys.treinamentojava.annotations.NaoNulo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NaoNulo
    @IniciarComLetra("C")
    @Column(name = "nome")
    private String nome;

    @IdadeMinima(25)
    @Column(name = "idade")
    private Integer idade;

}
