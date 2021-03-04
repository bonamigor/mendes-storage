import br.com.mendes.controller.ClienteController;
import br.com.mendes.model.Cliente;
import br.com.mendes.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest extends StorageApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ClienteController clienteController;

    @MockBean
    private ClienteService clienteService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void getAllClientes() throws Exception {
        List<Cliente> clienteList = new ArrayList<Cliente>();
        clienteList.add(new Cliente(1L, "Juarez", "47122285049"));
        clienteList.add(new Cliente(2L, "Rafael", "70102631166"));

        when(clienteService.listarTodosClientes()).thenReturn(clienteList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    public void criarClienteComSucesso() throws Exception {
        Cliente cliente = new Cliente(2L, "Rafael Bonamigo", "70102631166");
        when(clienteService.salvar(any(Cliente.class))).thenReturn(cliente);
        ObjectMapper objectMapper = new ObjectMapper();
        String eatToDoJSON = objectMapper.writeValueAsString(cliente);

        ResultActions result = mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eatToDoJSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Rafael Bonamigo"))
                .andExpect(jsonPath("$.cpf").value("70102631166"));
    }

}
