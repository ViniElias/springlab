package br.gov.sp.cps.spring_teste.service;

import java.util.List;

import br.gov.sp.cps.spring_teste.entity.Disciplina;

public interface DisciplinaService {
    public Disciplina cadastrar(Disciplina disciplina);
    public List<Disciplina> listar();
    public Disciplina buscarPorId(Long id);
}
