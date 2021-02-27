import br.com.mendes.model.Cliente;
import br.com.mendes.repository.ClienteRepository;
import br.com.mendes.service.ClienteService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClienteServiceTest extends StorageApplicationTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void getAllClientes() {
        Cliente cliente = new Cliente(1L, "Juarez Bonamigo", "47122285049");
        clienteRepository.save(cliente);
        ClienteService clienteService = new ClienteService(clienteRepository);
        Cliente clienteResult = clienteService.findAll().get(0);

        assertEquals(cliente.getNome(), clienteResult.getNome());
        assertEquals(cliente.getCodigo(), clienteResult.getCodigo());
    }

    @Test
    public void salvarCliente() {
        ClienteService clienteService = new ClienteService(clienteRepository);
        Cliente clienteSample = new Cliente(2L, "Ana Maria", "03435984765");
        clienteService.save(clienteSample);

        assertEquals(2.0, clienteRepository.count());
    }

    @AfterEach
    public void depoisDeTudo() {
        clienteRepository.deleteAll();
    }

}
