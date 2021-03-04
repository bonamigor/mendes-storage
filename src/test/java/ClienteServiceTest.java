import br.com.mendes.model.Cliente;
import br.com.mendes.repository.ClienteRepository;
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
        Cliente clienteResult = clienteRepository.findAll().get(0);

        assertEquals(cliente.getNome(), clienteResult.getNome());
        assertEquals(cliente.getCodigo(), clienteResult.getCodigo());
    }

    @Test
    public void salvarCliente() {
        Cliente clienteSample = new Cliente(2L, "Ana Maria", "03435984765");
        clienteRepository.save(clienteSample);

        assertEquals(2.0, clienteRepository.count());
    }

    @AfterEach
    public void depoisDeTudo() {
        clienteRepository.deleteAll();
    }

}
