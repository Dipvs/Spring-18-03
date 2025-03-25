package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme salvar(Filme filme) {
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser nulo");
        }
        return filmeRepository.save(filme);
    }

    @Transactional(readOnly = true)
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Filme buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));
    }

    public Filme atualizar(Filme filme) {
        if (filme == null || filme.getId() == null) {
            throw new IllegalArgumentException("Filme ou ID do filme não pode ser nulo");
        }

        if (!filmeRepository.existsById(filme.getId())) {
            throw new RuntimeException("Filme não encontrado com ID: " + filme.getId());
        }

        return filmeRepository.save(filme);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        if (!filmeRepository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado com ID: " + id);
        }

        filmeRepository.deleteById(id);
    }
}