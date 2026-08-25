package br.gov.sp.cps.spring_teste.service;

import java.util.List;

import br.gov.sp.cps.spring_teste.entity.Curso;

public interface CursoService {
    public Curso cadastrar(Curso curso);
    public List<Curso> listar();
    public Curso buscarPorId(Long id);
}
    