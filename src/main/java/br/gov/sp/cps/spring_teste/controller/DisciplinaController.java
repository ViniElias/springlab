package br.gov.sp.cps.spring_teste.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.cps.spring_teste.entity.Disciplina;
import br.gov.sp.cps.spring_teste.service.DisciplinaService;

@RestController
@CrossOrigin
@RequestMapping("/disciplina")
public class DisciplinaController {
    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina) {
        Disciplina novo = service.cadastrar(disciplina);
        return ResponseEntity.created(URI.create("/disciplina/" + novo.getId())).body(novo);
    }

    @GetMapping
    public List<Disciplina> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Disciplina buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/pesquisa")
    public Disciplina buscarPorIdParam(@RequestParam("id") Long id) {
        return service.buscarPorId(id);
    }
}