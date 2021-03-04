package br.com.mendes.controller;

import br.com.mendes.model.Lancamento;
import br.com.mendes.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        lancamentoService.salvar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    }

    @GetMapping
    public List<Lancamento> listarTodosOsLancamentos() {
        return lancamentoService.listarTodosLancamentos();
    }

    @GetMapping("/{id}")
    public Optional<Lancamento> recuperarLancamento(@PathVariable Long id) {
        return lancamentoService.recuperarLancamento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {
        Lancamento lancamentoSalvo = lancamentoService.atualizar(id, lancamento);
        return ResponseEntity.ok(lancamentoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        lancamentoService.excluir(id);
    }
}
