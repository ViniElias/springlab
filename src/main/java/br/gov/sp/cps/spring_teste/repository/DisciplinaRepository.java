package br.gov.sp.cps.spring_teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.cps.spring_teste.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
}
