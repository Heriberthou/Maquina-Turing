package moniTech.dominio;
/*
* Autor: Heriberto Gómez Bolaina
* Fecha: 24/05/2024
* Objetivo: Crear una clase llamada Monitor que represente las características de un monitor,
* como el año de fabricación, el precio, el modelo, el número de serie, la fecha de lanzamiento,
* la forma de la pantalla, la marca, los tipos de puerto, los colores disponibles y la imagen.
* Crear métodos para establecer y obtener los valores, un constructor sin parámetros para 
* inicializar valores por defecto y un método toString para obtener una representación
* en cadena del monitor.
*/

import java.util.*;

public class Monitor {
    // Número libre: Representa el año de fabricación del monitor.
    private int añoFabricacion;
    // Número con rango: Representa el precio del monitor.
    private double precio;
    // Texto libre: Representa el modelo del monitor.
    private String modelo;
    // Texto con formato: Representa el número de serie del monitor.
    private String numeroSerie;
    // Fecha: Representa la fecha de lanzamiento del monitor.
    private Date fechaLanzamiento;
    // Opciones mutuamente excluyentes fijas: Representa la forma de la pantalla del monitor.
    private String formaPantalla; //Pantalla Plana o Curva.
    // Opciones mutuamente excluyentes dinámicas: Representa la marca del monitor.
    private String marca;
	// Multivalorado fijo: Representa los tipos de puertos del monitor.
    private ArrayList<String> tiposDePuerto; //VGA, DVI, HDMI
    // Multivalorado dinámico: Representa los colores del monitor.
    private ArrayList<String> colores;
    // Imagen: Representa la imagen del monitor.
    private String imagen;

    /**
     * Obtiene el año de fabricación del monitor.
     */
    public int getAñoFabricacion() {
        return añoFabricacion;
    }

    /**
     * Establece el año de fabricación del monitor.
     */
    public void setAñoFabricacion(String añoFabricacion) {
        // Convierte el año de String a int y llama al segundo setAñoFabricacion
    	añoFabricacion = añoFabricacion.trim();
        int año = Integer.parseInt(añoFabricacion);
        setAñoFabricacion(año);
    }

    /**
     * Establece el año de fabricación del monitor.
     */
    public void setAñoFabricacion(int añoFabricacion) {
        // Establece el año de fabricación
        this.añoFabricacion = añoFabricacion;
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
     * lanza la IllegalArgumentException Si el precio está fuera del rango especificado.
     */
    public void setPrecio(double precio) {
        // Valida si el precio está dentro del rango especificado y establece el precio
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
     * lanza la IllegalArgumentException Si el modelo está vacío.
     */
    public void setModelo(String modelo) {
        // Quita los espacios de los extremos y valida que no esté vacío el dato.
        modelo = modelo.trim();
        if (modelo.isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        } else {
            this.modelo = modelo;
        }
    }

    /**
     * Obtiene el número de serie del monitor.
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }
    
    /**
     * Establece el número de serie del monitor.
     * lanza la excepciónIllegalArgumentException Si el formato del número de serie es inválido.
     */
    public void setNumeroSerie(String numeroSerie) {
        // Quita los espacios de los extremos y valida el formato del dato.
        numeroSerie = numeroSerie.trim();
        numeroSerie = numeroSerie.toUpperCase();
        if (numeroSerie.matches("[0-9]{3}[A-Z]{4}[0-9A-Z]{5}")) {
            this.numeroSerie = numeroSerie;
        } else {
            throw new IllegalArgumentException("El formato del número de serie es inválido.");
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
     * Constructor sin parámetros que establece valores por defecto para cada variable.
     */
    public Monitor() {
        this.añoFabricacion = 0; 
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
     * Regresa la representación en cadena de la entidad Monitor.
     */
    public String toString() {
        return marca + " " + modelo + " " + formaPantalla;
    }
    
}
