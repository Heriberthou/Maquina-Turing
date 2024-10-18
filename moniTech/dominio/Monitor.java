package moniTech.dominio;
/*
* Autor: Heriberto G�mez Bolaina
* Fecha: 24/05/2024
* Objetivo: Crear una clase llamada Monitor que represente las caracter�sticas de un monitor,
* como el a�o de fabricaci�n, el precio, el modelo, el n�mero de serie, la fecha de lanzamiento,
* la forma de la pantalla, la marca, los tipos de puerto, los colores disponibles y la imagen.
* Crear m�todos para establecer y obtener los valores, un constructor sin par�metros para 
* inicializar valores por defecto y un m�todo toString para obtener una representaci�n
* en cadena del monitor.
*/

import java.util.*;

public class Monitor {
    // N�mero libre: Representa el a�o de fabricaci�n del monitor.
    private int a�oFabricacion;
    // N�mero con rango: Representa el precio del monitor.
    private double precio;
    // Texto libre: Representa el modelo del monitor.
    private String modelo;
    // Texto con formato: Representa el n�mero de serie del monitor.
    private String numeroSerie;
    // Fecha: Representa la fecha de lanzamiento del monitor.
    private Date fechaLanzamiento;
    // Opciones mutuamente excluyentes fijas: Representa la forma de la pantalla del monitor.
    private String formaPantalla; //Pantalla Plana o Curva.
    // Opciones mutuamente excluyentes din�micas: Representa la marca del monitor.
    private String marca;
	// Multivalorado fijo: Representa los tipos de puertos del monitor.
    private ArrayList<String> tiposDePuerto; //VGA, DVI, HDMI
    // Multivalorado din�mico: Representa los colores del monitor.
    private ArrayList<String> colores;
    // Imagen: Representa la imagen del monitor.
    private String imagen;

    /**
     * Obtiene el a�o de fabricaci�n del monitor.
     */
    public int getA�oFabricacion() {
        return a�oFabricacion;
    }

    /**
     * Establece el a�o de fabricaci�n del monitor.
     */
    public void setA�oFabricacion(String a�oFabricacion) {
        // Convierte el a�o de String a int y llama al segundo setA�oFabricacion
    	a�oFabricacion = a�oFabricacion.trim();
        int a�o = Integer.parseInt(a�oFabricacion);
        setA�oFabricacion(a�o);
    }

    /**
     * Establece el a�o de fabricaci�n del monitor.
     */
    public void setA�oFabricacion(int a�oFabricacion) {
        // Establece el a�o de fabricaci�n
        this.a�oFabricacion = a�oFabricacion;
    }

    /**
     * Obtiene el precio del monitor.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del monitor.
     */
    public void setPrecio(String precio) {
        // Convierte el precio de String a double y llama al segundo setPrecio
    	precio = precio.trim();
        double precioD = Double.parseDouble(precio);
        setPrecio(precioD);
    }

    /**
     * Establece el precio del monitor.
     * lanza la IllegalArgumentException Si el precio est� fuera del rango especificado.
     */
    public void setPrecio(double precio) {
        // Valida si el precio est� dentro del rango especificado y establece el precio
        if (precio >= 2000 && precio <= 80000) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio debe estar entre 2000 y 80000.");
        }
    }

    /**
     * Obtiene el modelo del monitor.
     */
    public String getModelo() {
        return modelo;
    }
    
    /**
     * Establece el modelo del monitor.
     * lanza la IllegalArgumentException Si el modelo est� vac�o.
     */
    public void setModelo(String modelo) {
        // Quita los espacios de los extremos y valida que no est� vac�o el dato.
        modelo = modelo.trim();
        if (modelo.isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vac�o.");
        } else {
            this.modelo = modelo;
        }
    }

    /**
     * Obtiene el n�mero de serie del monitor.
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }
    
    /**
     * Establece el n�mero de serie del monitor.
     * lanza la excepci�nIllegalArgumentException Si el formato del n�mero de serie es inv�lido.
     */
    public void setNumeroSerie(String numeroSerie) {
        // Quita los espacios de los extremos y valida el formato del dato.
        numeroSerie = numeroSerie.trim();
        numeroSerie = numeroSerie.toUpperCase();
        if (numeroSerie.matches("[0-9]{3}[A-Z]{4}[0-9A-Z]{5}")) {
            this.numeroSerie = numeroSerie;
        } else {
            throw new IllegalArgumentException("El formato del n�mero de serie es inv�lido.");
        }
    }
    
    /**
     * Obtiene la fecha de lanzamiento del monitor.
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * Establece la fecha de lanzamiento del monitor.
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Obtiene la forma de pantalla del monitor.
     */
    public String getFormaPantalla() {
        return formaPantalla;
    }

    /**
     * Establece la forma de pantalla del monitor.
     */
    public void setFormaPantalla(String formaPantalla) {
        this.formaPantalla = formaPantalla;
    }

    /**
     * Obtiene la marca del monitor.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del monitor.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
	
    /**
     * Obtiene los tipos de puerto del monitor.
     */
    public ArrayList<String> getTiposDePuerto() {
        return tiposDePuerto;
    }

    /**
     * Establece los tipos de puerto del monitor.
     */
    public void setTiposDePuerto(ArrayList<String> tipoPuerto) {
        this.tiposDePuerto = tipoPuerto;
    }

    /**
     * Obtiene los colores del monitor.
     */
    public ArrayList<String> getColores() {
        return colores;
    }

    /**
     * Establece los colores del monitor.
     */
    public void setColores(ArrayList<String> color) {
        this.colores = color;
    }

    /**
     * Obtiene la imagen del monitor.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen del monitor.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Constructor sin par�metros que establece valores por defecto para cada variable.
     */
    public Monitor() {
        this.a�oFabricacion = 0; 
        this.precio = 0.0; 
        this.modelo = ""; 
        this.numeroSerie = ""; 
        this.fechaLanzamiento = null; 
        this.formaPantalla = ""; 
        this.marca = ""; 
        this.tiposDePuerto = null; 
        this.colores = null;
        this.imagen = ""; 
    }
    
    /**
     * Regresa la representaci�n en cadena de la entidad Monitor.
     */
    public String toString() {
        return marca + " " + modelo + " " + formaPantalla;
    }
    
}
