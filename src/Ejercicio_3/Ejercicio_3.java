package Ejercicio_3;

interface MetodoPago {
    boolean procesarPago(double monto);
}

class PagoTarjeta implements MetodoPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago con tarjeta: $" + monto);
        return true;
    }
}

class PagoPayPal implements MetodoPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago con PayPal: $" + monto);
        return false;
    }
}

class PagoCripto implements MetodoPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago con criptomonedas: $" + monto);
        return true;
    }
}

class PlataformaPagos {
    public void realizarPago(MetodoPago metodo, double monto) {
        boolean exito = metodo.procesarPago(monto);
        if (exito) {
            System.out.println("Pago realizado con éxito.\n");
        } else {
            System.out.println("Error al procesar el pago.");
            System.out.println("Reintentando...\n");

            if (metodo.procesarPago(monto)) {
                System.out.println("Pago realizado exitosamente en el reintento.\n");
            } else {
                System.out.println("El pago falló nuevamente.\n");
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        PlataformaPagos plataforma = new PlataformaPagos();

        MetodoPago tarjeta = new PagoTarjeta();
        MetodoPago paypal = new PagoPayPal();
        MetodoPago cripto = new PagoCripto();

        plataforma.realizarPago(tarjeta, 100.00);
        plataforma.realizarPago(paypal, 50.00);
        plataforma.realizarPago(cripto, 150.00);
    }
}
