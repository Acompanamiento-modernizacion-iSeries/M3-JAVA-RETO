package banco;

import db.ClienteDB;
import db.TransaccionDB;
import clientes.Cliente;
import java.math.BigDecimal;
import java.util.List;

public class Banco {
    private ClienteDB clienteDB;
    private TransaccionDB transaccionDB;

    public Banco(ClienteDB clienteDB, TransaccionDB transaccionDB) {
        this.clienteDB = clienteDB;
        this.transaccionDB = transaccionDB;
    }
    public void agregarCliente(String nombre, String id, String tipoCuenta, BigDecimal saldoInicial) {
        Cliente cliente;

        if (tipoCuenta.equalsIgnoreCase("B")) {
            cliente = new Cliente(nombre, id, new cuentas.CuentaBasica(id, saldoInicial));
        } else {
            cliente = new Cliente(nombre, id, new cuentas.CuentaPremium(id, saldoInicial));
        }

        clienteDB.agregarCliente(cliente);
    }

    public Cliente buscarCliente(String id) {
        return clienteDB.buscarClientePorId(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDB.obtenerTodosLosClientes();
    }
}
