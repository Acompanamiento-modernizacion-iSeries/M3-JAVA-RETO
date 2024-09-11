package banco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import clientes.Cliente;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;

public class Banco {
    private List<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(String nombre, String id, String tipoCuenta, BigDecimal saldoInicial) {
        Cliente cliente;
        if (tipoCuenta.equalsIgnoreCase("B")) {
            cliente = new Cliente(nombre, id, new CuentaBasica(id, saldoInicial));
        } else {
            cliente = new Cliente(nombre, id, new CuentaPremium(id, saldoInicial));
        }
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }
}