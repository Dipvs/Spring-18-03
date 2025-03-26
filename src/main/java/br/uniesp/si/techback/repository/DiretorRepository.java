package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    List<Diretor> findByNomeContainingIgnoreCase(String nome);
    List<Diretor> findByNacionalidade(String nacionalidade);
}