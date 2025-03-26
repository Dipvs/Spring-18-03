package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorService diretorService;

    @Autowired
    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diretor salvar(@RequestBody Diretor diretor) {
        return diretorService.salvar(diretor);
    }

    @GetMapping
    public List<Diretor> listar() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Diretor buscarPorId(@PathVariable Long id) {
        return diretorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Diretor atualizar(@PathVariable Long id, @RequestBody Diretor diretor) {
        diretor.setId(id);
        return diretorService.atualizar(diretor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        diretorService.deletar(id);
    }

    @GetMapping("/buscar")
    public List<Diretor> buscarPorNome(@RequestParam String nome) {
        return diretorService.buscarPorNome(nome);
    }

    @GetMapping("/nacionalidade/{nacionalidade}")
    public List<Diretor> buscarPorNacionalidade(@PathVariable String nacionalidade) {
        return diretorService.buscarPorNacionalidade(nacionalidade);
    }
}