package db;

import clientes.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDBArrayList implements ClienteDB {
    private List<Cliente> clientes;

    public ClienteDBArrayList() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public Cliente buscarClientePorId(String id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return new ArrayList<>(clientes);
    }
}
