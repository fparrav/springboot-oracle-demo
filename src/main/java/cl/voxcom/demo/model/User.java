package cl.voxcom.demo.model;

import java.io.Serializable;

/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */
public class User implements Serializable {
    String rut;
    String dv;
    String nombre;
    String apellido;

    public  User(){

    }


    public User(String rut, String dv, String nombre, String apellido) {
        this.rut = rut;
        this.dv = dv;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }


    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
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


}
