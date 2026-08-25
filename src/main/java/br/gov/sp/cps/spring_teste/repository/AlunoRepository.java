package br.gov.sp.cps.spring_teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.cps.spring_teste.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}