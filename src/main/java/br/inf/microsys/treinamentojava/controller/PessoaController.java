package br.inf.microsys.treinamentojava.controller;

import br.inf.microsys.treinamentojava.model.Pessoa;
import br.inf.microsys.treinamentojava.repository.PessoaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pessoas")
@RestController
public class PessoaController extends BaseController<Pessoa, PessoaRepository> {

}
