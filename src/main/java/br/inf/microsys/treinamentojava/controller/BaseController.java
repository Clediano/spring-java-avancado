package br.inf.microsys.treinamentojava.controller;

import br.inf.microsys.treinamentojava.annotations.IdadeMinima;
import br.inf.microsys.treinamentojava.annotations.IniciarComLetra;
import br.inf.microsys.treinamentojava.annotations.NaoNulo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BaseController<Entity, Repository extends JpaRepository<Entity, Integer>> {

    @Autowired
    private Repository repository;

    @GetMapping
    public List<Entity> buscar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(repository.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Entity entity) {
        validar(entity);
        return ResponseEntity.ok(repository.save(entity));
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<?> atualizar(@PathVariable("id") Integer id, @RequestBody Entity entity) {

        Method method = entity.getClass().getDeclaredMethod("setId", Integer.class);
        method.invoke(entity, id);

        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    private void validar(Entity entity) {
        validarNaoNulo(entity);
        validarInicialCom(entity);
        validarIdadeMinima(entity);
    }

    @SneakyThrows
    private void validarNaoNulo(Entity entity) {
        Class<?> classe = entity.getClass();

        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(NaoNulo.class)) {
                field.setAccessible(true);
                Object value = field.get(entity);
                field.setAccessible(false);

                if (value == null) {
                    throw new RuntimeException("O campo " + field.getName() + " não deve ser null.");
                }
            }
        }
    }

    @SneakyThrows
    private void validarInicialCom(Entity entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IniciarComLetra.class)) {
                field.setAccessible(true);
                if (field.get(entity) instanceof String) {
                    String valorDoCampo = (String) field.get(entity);
                    field.setAccessible(false);

                    String letraInicial = field.getAnnotation(IniciarComLetra.class).value();

                    if (valorDoCampo == null || !valorDoCampo.toUpperCase().startsWith(letraInicial.toUpperCase())) {
                        throw new RuntimeException("O campo " + field.getName() + " precisa iniciar com a letra '" + letraInicial + "'");
                    }
                } else {
                    throw new RuntimeException("A anotação IniciarComLetra precisa estar em campo string.");
                }
            }
        }
    }

    @SneakyThrows
    private void validarIdadeMinima(Entity entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IdadeMinima.class)) {
                field.setAccessible(true);
                if (field.get(entity) instanceof Integer) {
                    Integer valorCampo = (Integer) field.get(entity);
                    Integer idadeMinima = field.getAnnotation(IdadeMinima.class).value();

                    if (valorCampo == null || valorCampo < idadeMinima) {
                        throw new Exception("Impossível cadastrar idade com idade menor que " + idadeMinima);
                    }
                } else {
                    throw new RuntimeException("A anotação IdadeMinima precisa estar em campo Integer.");
                }
            }
        }
    }
}
