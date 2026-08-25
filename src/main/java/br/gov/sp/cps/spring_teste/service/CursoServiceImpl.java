package br.gov.sp.cps.spring_teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.spring_teste.entity.Curso;
import br.gov.sp.cps.spring_teste.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository repo;

    public CursoServiceImpl(CursoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Curso cadastrar(Curso curso) {
        if(curso == null ||
            curso.getId() != null ||
            curso.getNome() == null ||
            curso.getNome().isBlank() ||
            curso.getSigla() == null ||
            curso.getSigla().isBlank()
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do curso inválidos.");
        };
        return repo.save(curso);
    }  

    @Override
    public List<Curso> listar() {
        return repo.findAll();
    }    

    @Override
    public Curso buscarPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do curso não pode ser nulo.");
        }
        Optional<Curso> cursoOp = repo.findById(id);
        if(cursoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado.");
        }
        return cursoOp.get();
    }    
}
