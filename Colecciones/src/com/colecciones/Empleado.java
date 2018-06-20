/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

/**
 *
 * @author FABAME
 */
public class Empleado {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer salario;

    public Empleado() {
        this.id = 0L;
        this.nombre = "";
        this.apellido = "";
        this.salario = 0;
    }

    public Empleado(String nombre, String apellido, Integer salario) {
        this.id = 0L;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }
    
     public Empleado(Long id, String nombre, String apellido, Integer salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", salario=" + salario + '}';
    }

}
