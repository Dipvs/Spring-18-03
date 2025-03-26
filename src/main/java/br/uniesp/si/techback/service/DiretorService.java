package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiretorService {

    private final DiretorRepository diretorRepository;

    @Autowired
    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    public Diretor salvar(Diretor diretor) {
        if (diretor == null) {
            throw new IllegalArgumentException("Diretor não pode ser nulo");
        }
        return diretorRepository.save(diretor);
    }

    @Transactional(readOnly = true)
    public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Diretor buscarPorId(Long id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado com ID: " + id));
    }

    public Diretor atualizar(Diretor diretor) {
        if (diretor.getId() == null) {
            throw new IllegalArgumentException("ID do diretor não pode ser nulo");
        }
        buscarPorId(diretor.getId());
        return diretorRepository.save(diretor);
    }

    public void deletar(Long id) {
        Diretor diretor = buscarPorId(id);
        diretorRepository.delete(diretor);
    }

    @Transactional(readOnly = true)
    public List<Diretor> buscarPorNome(String nome) {
        return diretorRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<Diretor> buscarPorNacionalidade(String nacionalidade) {
        return diretorRepository.findByNacionalidade(nacionalidade);
    }
}