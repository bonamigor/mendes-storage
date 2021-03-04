package br.com.mendes.service;

import br.com.mendes.model.Cliente;
import br.com.mendes.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> recuperarCliente(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteSalvo = buscaClienteExistente(id);
        BeanUtils.copyProperties(cliente, clienteSalvo, "id");
        return clienteRepository.save(clienteSalvo);
    }

    public void excluir(Long id) {
        clienteRepository.delete(buscaClienteExistente(id));
    }

    private Cliente buscaClienteExistente(Long id) {
        Cliente clienteSalvo = clienteRepository.getOne(id);
        if (clienteSalvo == null) {
            throw new IllegalArgumentException();
        }
        return clienteSalvo;
    }

}
