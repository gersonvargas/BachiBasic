package com.herprogramacion.geekyweb;

import java.util.ArrayList;

/**
 * Created by Guaria on 5/15/2016.
 */
public class Pregunta {
    String descripcion;
    String []opciones;
    int numero;
    int respuesta;

    public Pregunta(String descripcion,String []opciones, int numero, int respuesta) {
        this.descripcion = descripcion;
        this.opciones=opciones;
        this.numero = numero;
        this.respuesta = respuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}
