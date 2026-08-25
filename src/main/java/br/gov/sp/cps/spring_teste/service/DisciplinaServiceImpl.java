package br.gov.sp.cps.spring_teste.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.spring_teste.entity.Aluno;
import br.gov.sp.cps.spring_teste.entity.Disciplina;
import br.gov.sp.cps.spring_teste.repository.DisciplinaRepository;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{
    private final DisciplinaRepository repo;

    private final CursoService cursoService;

    private final AlunoService alunoService;

    public DisciplinaServiceImpl(DisciplinaRepository repo, CursoService cursoService, AlunoService alunoService){
        this.repo = repo;
        this.cursoService = cursoService;
        this.alunoService = alunoService;
    }

    @Override
    public Disciplina cadastrar(Disciplina disciplina) {
        if (disciplina == null || 
                disciplina.getId() != null ||
                disciplina.getNome() == null ||
                disciplina.getNome().isBlank() ||
                disciplina.getCodigo() == null ||
                disciplina.getCodigo().isBlank() ||
                disciplina.getCurso() == null ||
                disciplina.getCurso().getId() == null
            ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da disciplina inválidos");
        };

        disciplina.setCurso(cursoService.buscarPorId(disciplina.getCurso().getId()));

        if (disciplina.getAlunos() != null){
            disciplina.getAlunos().forEach(aluno -> {
                alunoService.buscarPorId(aluno.getId());
            });
        }

        return repo.save(disciplina);
    }

    @Override
    public List<Disciplina> listar() {
        return repo.findAll();
    }

    @Override
    public Disciplina buscarPorId(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<Disciplina> discOp = repo.findById(id);
        if (discOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return discOp.get();
    }

    @Override
    public void matricularAluno(Long disciplinaId, Long alunoId) {
        Disciplina disciplina = buscarPorId(disciplinaId);
        if(disciplina.getAlunos() == null){
            disciplina.setAlunos(new HashSet<Aluno>());
        }
        disciplina.getAlunos().add(alunoService.buscarPorId(alunoId));
        repo.save(disciplina);
    }
}