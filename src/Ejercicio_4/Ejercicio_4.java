package Ejercicio_4;

import java.util.List;
import java.util.Arrays;

interface Evaluable {
    double evaluarDesempeno();
}

abstract class Participante implements Evaluable {
    String nombre;
    int edad;
    int id;

    public Participante(String nombre, int edad, int id) {
        if (nombre == null || nombre.isEmpty() || edad < 0 || id < 0) {
            System.out.println("Datos inválidos del participante.");
            return;
        }
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }

    public abstract void mostrar();
}

class Estudiante extends Participante {
    List<Double> calificaciones;

    public Estudiante(String nombre, int edad, int id, List<Double> calificaciones) {
        super(nombre, edad, id);
        for (double nota : calificaciones) {
            if (nota < 0 || nota > 10) {
                System.out.println("Error: Calificación fuera de rango.");
                return;
            }
        }
        this.calificaciones = calificaciones;
    }

    @Override
    public void mostrar() {
        System.out.println("Estudiante: " + nombre + " | Calificaciones: " + calificaciones);
    }

    @Override
    public double evaluarDesempeno() {
        return calificaciones.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}

class Docente extends Participante {
    double salario;
    List<String> cursos;
    int horasTrabajadas;

    public Docente(String nombre, int edad, int id, double salario, List<String> cursos, int horasTrabajadas) {
        super(nombre, edad, id);
        if (salario < 0 || horasTrabajadas < 0) {
            System.out.println("Error: Salario u horas inválidas.");
            return;
        }
        this.salario = salario;
        this.cursos = cursos;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public void mostrar() {
        System.out.println("Docente: " + nombre + " | Cursos: " + cursos + " | Horas: " + horasTrabajadas);
    }

    @Override
    public double evaluarDesempeno() {
        return horasTrabajadas;
    }
}

class Administrador extends Participante {
    double salario;
    String departamento;
    int horasTrabajadas;

    public Administrador(String nombre, int edad, int id, double salario, String departamento, int horasTrabajadas) {
        super(nombre, edad, id);
        if (salario < 0 || horasTrabajadas < 0 || departamento == null || departamento.isEmpty()) {
            System.out.println("Error: Datos inválidos del administrativo.");
            return;
        }
        this.salario = salario;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public void mostrar() {
        System.out.println("Administrador: " + nombre + " | Departamento: " + departamento + " | Horas: " + horasTrabajadas);
    }

    @Override
    public double evaluarDesempeno() {
        return horasTrabajadas;
    }
}

class SistemaAcademico {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("Juan Andres", 20, 101, Arrays.asList(8.0, 9.0, 7.0));
        Docente docente = new Docente("Laura Sofia", 35, 201, 3000.0, Arrays.asList("Electronica", "Base de Datos"), 36);
        Administrador admin = new Administrador("Mario Castillo", 45, 301, 2800.0, "Finanzas", 58);

        List<Participante> participantes = Arrays.asList(estudiante, docente, admin);

        for (Participante p : participantes) {
            p.mostrar();
            System.out.println("Desempeño: " + p.evaluarDesempeno());
            System.out.println("=========================================================================");

        }
    }
}
