package br.inf.microsys.treinamentojava.controller;

import br.inf.microsys.treinamentojava.model.Produto;
import br.inf.microsys.treinamentojava.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/produtos")
@RestController
public class ProdutoController extends BaseController<Produto, ProdutoRepository> {

}
