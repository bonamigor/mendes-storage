package br.com.mendes.service;

import br.com.mendes.model.Lancamento;
import br.com.mendes.repository.LancamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public List<Lancamento> listarTodosLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscaLancamentoExistente(id);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
        return lancamentoRepository.save(lancamentoSalvo);
    }

    public void excluir(Long id) {
        lancamentoRepository.delete(buscaLancamentoExistente(id));
    }

    private Lancamento buscaLancamentoExistente(Long id) {
        Lancamento lancamentoSalvo = lancamentoRepository.getOne(id);
        if (lancamentoSalvo == null) {
            throw new IllegalArgumentException();
        }
        return lancamentoSalvo;
    }

}
