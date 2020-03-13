package br.inf.microsys.treinamentojava.repository;

import br.inf.microsys.treinamentojava.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
