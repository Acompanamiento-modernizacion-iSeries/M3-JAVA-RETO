class CuentaBasica extends Cuenta {
    public CuentaBasica(String numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoSucursal(double monto) {
        this.saldo += monto;
        registrarTransaccion("Depósito en sucursal", monto);
    }

    @Override
    public void depositoCajero(double monto) {
        if (this.saldo < monto + 2) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        this.saldo += monto - 2;
        registrarTransaccion("Depósito en cajero", monto);
    }

    @Override
    public void depositoCuenta(double monto) {
        if (this.saldo < monto + 1.5) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        this.saldo += monto - 1.5;
        registrarTransaccion("Depósito desde otra cuenta", monto);
    }

    @Override
    public void compraFisico(double monto) {
        if (this.saldo < monto) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        this.saldo -= monto;
        registrarTransaccion("Compra en establecimiento físico", monto);
    }

    @Override
    public void compraWeb(double monto) {
        if (this.saldo < monto + 5) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        this.saldo -= monto + 5;
        registrarTransaccion("Compra en página web", monto);
    }

    @Override
    public void retiroCajero(double monto) {
        if (this.saldo < monto + 1) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        this.saldo -= monto + 1;
        registrarTransaccion("Retiro en cajero", monto);
    }
}