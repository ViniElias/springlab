package br.gov.sp.cps.spring_teste.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.spring_teste.entity.Disciplina;
import br.gov.sp.cps.spring_teste.repository.DisciplinaRepository;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    private final DisciplinaRepository repo;
    private final CursoService cursoService;
    private final AlunoService alunoService;

    public DisciplinaServiceImpl(DisciplinaRepository repo, CursoService cursoService, AlunoService alunoService) {
        this.repo = repo;
        this.cursoService = cursoService;
        this.alunoService = alunoService;
    }

    @Override
    public Disciplina cadastrar(Disciplina disciplina) {
        if(disciplina == null ||
                disciplina.getId() != null ||
                disciplina.getNome() == null ||
                disciplina.getNome().isBlank() ||
                disciplina.getCodigo() == null ||
                disciplina.getCodigo().isBlank() ||
                disciplina.getCurso() == null ||
                disciplina.getCurso().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da disciplina inválidos.");
        };
        disciplina.setCurso(cursoService.buscarPorId(disciplina.getCurso().getId()));
        if(disciplina.getAlunos() != null) {
            disciplina.getAlunos().forEach(aluno -> {
                alunoService.buscarPorId(aluno.getId());
            });
        }
        try {
            repo.save(disciplina);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar disciplina.");
        }
        return repo.save(disciplina);
    }

    @Override
    public List<Disciplina> listar() {
        return repo.findAll();
    }

    @Override
    public Disciplina buscarPorId(Long id) {
        return null;
    }
}
