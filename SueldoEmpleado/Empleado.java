import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * Clase Empleado
 * Heriberto Gomez Bolaina
 * 09/10/2024
 */
public abstract class Empleado implements Comparable<Empleado> {

	private int numeroEmpleado;
	private String curp;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	public static final int POR_NOMBRES = 1;
	public static final int POR_APELLIDOS = 2;
	public static final int POR_NUMERO_EMPLEADO = 3;

	private static int tipoOrdenacion = POR_NOMBRES;

	protected Empleado(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno) {

		setNumeroEmpleado(numeroEmpleado);

		setCurp(curp);
		setPrimerNombre(primerNombre);
		setSegundoNombre(segundoNombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
	}
	

	public static int getTipoOrdenacion() {
		return tipoOrdenacion;
	}

	public static void setTipoOrdenacion(int tipoOrdenacion) {
		Empleado.tipoOrdenacion = tipoOrdenacion;
	}
	
	public void setNumeroEmpleado(String numeroEmpleado) {
		setNumeroEmpleado(Integer.parseInt(numeroEmpleado));
	}

	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public String getCurp() {
		return curp;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/*
	 * retorna una cadena donde se concatena el significado y el valor de cada
	 * atributo en forma de lista.
	 */
	public String obtenerDetalles() {
		return "Número de empleado: " + getNumeroEmpleado() + "\n" + "Curp: " + getCurp() + "\n" + "Primer nombre: "
				+ getPrimerNombre() + "\n" + "Segundo nombre: " + getSegundoNombre() + "\n" + "Apellido Paterno: "
				+ getApellidoPaterno() + "\n" + "Apellido Materno: " + getApellidoMaterno();

	}

	public abstract double calcularSueldoMes();

	@Override
	public int compareTo(Empleado o) {
		if (tipoOrdenacion == POR_NOMBRES) {
			// Comparación por primerNombre
			int resultado = this.primerNombre.compareToIgnoreCase(o.primerNombre);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por segundoNombre
			resultado = this.segundoNombre.compareToIgnoreCase(o.segundoNombre);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por apellidoPaterno
			resultado = this.apellidoPaterno.compareToIgnoreCase(o.apellidoPaterno);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por apellidoMaterno
			return this.apellidoMaterno.compareToIgnoreCase(o.apellidoMaterno);

		} else if (tipoOrdenacion == POR_APELLIDOS) {
			// Comparación por apellidoPaterno
			int resultado = this.apellidoPaterno.compareToIgnoreCase(o.apellidoPaterno);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por apellidoMaterno
			resultado = this.apellidoMaterno.compareToIgnoreCase(o.apellidoMaterno);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por primerNombre
			resultado = this.primerNombre.compareToIgnoreCase(o.primerNombre);
			if (resultado != 0) {
				return resultado;
			}

			// Comparación por segundoNombre
			return this.segundoNombre.compareTo(o.segundoNombre);

		} else if (tipoOrdenacion == POR_NUMERO_EMPLEADO) {
			// Comparación por número de empleado
			return Integer.compare(this.numeroEmpleado, o.numeroEmpleado);
		}

		return 0;
	}
	
	 public String getSueldoMes(){
		 Formateador moneda = new Formateador();
		 return moneda.moneda(calcularSueldoMes());
	 }
	
	protected class Formateador {

		protected String moneda(double valor) {
			NumberFormat formato = NumberFormat.getCurrencyInstance();
			return formato.format(valor);
		}

		protected String decimal(float valor) {
			DecimalFormat formato = new DecimalFormat("0.0");
			return formato.format(valor);
		}
		
	}
	
	

}
