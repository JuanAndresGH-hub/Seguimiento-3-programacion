package Ejercicio_1;

abstract class Organismo {
    int energia;
    static final int energia_minima = 1;

    Organismo(int energia) {
        this.energia = energia;
    }

    boolean estaVivo() {
        return energia >= energia_minima;
    }

    abstract void actuar();
}

class Planta extends Organismo {
    Planta() {
        super(10);
    }

    void actuar() {
        if (estaVivo()) {
            energia += 5;
            System.out.println("La planta hace fotosíntesis (+5 energía).");
        } else {
            System.out.println("La planta está muerta y no puede hacer fotosíntesis.");
        }
    }
}

class Herbivoro extends Organismo {
    Planta planta;

    Herbivoro(Planta planta) {
        super(20);
        this.planta = planta;
    }

    void actuar() {
        if (estaVivo()) {
            if (planta.estaVivo()) {
                planta.energia -= 5;
                energia += 5;
                System.out.println("El herbívoro come una planta (+5 energía).");
            } else {
                energia -= 5;
                System.out.println("El herbívoro no encuentra ninguna planta. Pierde energía (-5).");
            }
        } else {
            System.out.println("El herbívoro está muerto y no puede actuar.");
        }
    }
}

class Carnivoro extends Organismo {
    Herbivoro herbivoro;

    Carnivoro(Herbivoro herbivoro) {
        super(25);
        this.herbivoro = herbivoro;
    }

    void actuar() {
        if (estaVivo()) {
            if (herbivoro.estaVivo()) {
                herbivoro.energia -= 10;
                energia += 10;
                System.out.println("El carnívoro caza a un herbívoro (+10 energía).");
            } else {
                energia -= 5;
                System.out.println("El carnívoro no encuentra ninguna presa. Pierde energía (-5).");
            }
        } else {
            System.out.println("El carnívoro está muerto y no puede actuar.");
        }
    }
}

class main {
    public static void main(String[] args) {
        Planta planta = new Planta();
        Herbivoro herbivoro = new Herbivoro(planta);
        Carnivoro carnivoro = new Carnivoro(herbivoro);

        for (int ciclo = 1; ciclo <= 5; ciclo++) {
            System.out.println("===== CICLO " + ciclo + " =====");

            planta.actuar();
            herbivoro.actuar();
            carnivoro.actuar();

            System.out.println("\nESTADO AL FINAL DEL CICLO " + ciclo + ":");
            System.out.println("Planta     - Energía: " + planta.energia + " - " + (planta.estaVivo() ? "Viva" : "Muerta"));
            System.out.println("Herbívoro  - Energía: " + herbivoro.energia + " - " + (herbivoro.estaVivo() ? "Vivo" : "Muerto"));
            System.out.println("Carnívoro  - Energía: " + carnivoro.energia + " - " + (carnivoro.estaVivo() ? "Vivo" : "Muerto"));
            System.out.println();
        }
    }
}
