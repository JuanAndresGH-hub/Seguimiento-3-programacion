package Ejercicio_2;

interface Dispositivo {
    void encender();
    void apagar();
    void mostrarEstado();
}

interface Recargable {
    void recargar();
}

abstract class DispositivoBase implements Dispositivo {
    protected boolean estaEncendido = false;

    public void encender() {
        estaEncendido = true;
        System.out.println(getClass().getSimpleName() + " encendido.");
    }

    public void apagar() {
        estaEncendido = false;
        System.out.println(getClass().getSimpleName() + " apagado.");
    }
}

class Laptop extends DispositivoBase implements Recargable {
    int bateria = 20;

    public void encender() {
        if (bateria <= 0) {
            System.out.println("No se puede encender la laptop. Batería agotada.");
            return;
        }
        estaEncendido = true;
        System.out.println("Laptop encendida.");
    }

    public void mostrarEstado() {
        System.out.println("Laptop - Batería: " + bateria + "% - Encendido: " + estaEncendido);
    }

    public void recargar() {
        if (estaEncendido) {
            System.out.println("Apaga la laptop antes de recargar.");
            return;
        }
        bateria = 100;
        System.out.println("Laptop recargada al 100%.");
    }
}

class Bombilla extends DispositivoBase {
    int intensidad = 50;

    public void mostrarEstado() {
        System.out.println("Bombilla - Intensidad: " + intensidad + "% - Encendido: " + estaEncendido);
    }
}

class Main {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        Bombilla bombilla = new Bombilla();

        laptop.encender();
        laptop.mostrarEstado();
        laptop.apagar();
        laptop.recargar();

        bombilla.encender();
        bombilla.mostrarEstado();
    }
}
