package br.gov.sp.cps.spring_teste.service;

import java.util.List;

import br.gov.sp.cps.spring_teste.entity.Aluno;

public interface AlunoService {
    public Aluno cadastrar(Aluno aluno);
    public List<Aluno> listar();
    public Aluno buscarPorId(Long id);
}
