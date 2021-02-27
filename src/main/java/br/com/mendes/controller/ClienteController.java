package br.com.mendes.controller;

import br.com.mendes.model.Cliente;
import br.com.mendes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/clientes")
    ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

}
