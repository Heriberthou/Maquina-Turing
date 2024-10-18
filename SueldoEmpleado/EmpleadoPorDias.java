
public class EmpleadoPorDias extends Empleado {
	private double sueldoDiario;
	private float diasTrabajadosMes;

	public EmpleadoPorDias(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String sueldoDiario, String diasTrabajadosMes) {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);
		setSueldoDiario(sueldoDiario);
		setDiasTrabajadosMes(diasTrabajadosMes);
	}

	public void setSueldoDiario(String sueldoDiario) {
		setSueldoDiario(Double.parseDouble(sueldoDiario));
	}

	public void setSueldoDiario(double sueldoDiario) {
		this.sueldoDiario = sueldoDiario;
	}

	public void setDiasTrabajadosMes(String diasTrabajadosMes) {
		setDiasTrabajadosMes(Float.parseFloat(diasTrabajadosMes));
	}

	public void setDiasTrabajadosMes(float diasTrabajadosMes) {
		this.diasTrabajadosMes = diasTrabajadosMes;
	}

	public double getSueldoDiario() {
		return sueldoDiario;
	}

	public float getDiasTrabajadosMes() {
		return diasTrabajadosMes;
	}

	/*
	 * retorna una cadena donde se concatena el significado y el valor de cada
	 * atributo en forma de lista.
	 */
	@Override
	public String obtenerDetalles() {
		Formateador formateo = new Formateador();
		return super.obtenerDetalles() + "\n" + "Sueldo diario: " + formateo.moneda(getSueldoDiario()) + "\n"
				+ "Días trabajados por mes: " + formateo.decimal(getDiasTrabajadosMes());
	}

	/*
	 * Calcula el sueldo mensual del empleado con el sueldoDiario y
	 * diasTrabajadosMes
	 */
	@Override
	public double calcularSueldoMes() {
		return getSueldoDiario() * getDiasTrabajadosMes();
	}

}
