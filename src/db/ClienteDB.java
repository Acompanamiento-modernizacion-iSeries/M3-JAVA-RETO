package db;

import clientes.Cliente;
import java.util.List;

public interface ClienteDB {
    void agregarCliente(Cliente cliente);
    Cliente buscarClientePorId(String id);
    List<Cliente> obtenerTodosLosClientes();
}
