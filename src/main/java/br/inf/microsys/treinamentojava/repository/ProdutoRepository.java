package br.inf.microsys.treinamentojava.repository;

import br.inf.microsys.treinamentojava.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
