package br.gov.sp.cps.spring_teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.cps.spring_teste.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}
