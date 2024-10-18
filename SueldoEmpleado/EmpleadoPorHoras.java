public class EmpleadoPorHoras extends Empleado {
	private float horasTrabajadasMes;
	private double sueldoPorHora;

	public EmpleadoPorHoras(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String horasTrabajadasMes, String sueldoPorHora) {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);
		setHorasTrabajadasMes(horasTrabajadasMes);
		setSueldoPorHora(sueldoPorHora);

	}

	public void setHorasTrabajadasMes(String horasTrabajadasMes) {
		setHorasTrabajadasMes(Float.parseFloat(horasTrabajadasMes));
	}

	public void setHorasTrabajadasMes(float horasTrabajadasMes) {
		this.horasTrabajadasMes = horasTrabajadasMes;
	}

	public void setSueldoPorHora(String sueldoPorHora) {
		setSueldoPorHora(Double.parseDouble(sueldoPorHora));
	}

	public void setSueldoPorHora(double sueldoPorHora) {
		this.sueldoPorHora = sueldoPorHora;
	}

	public float getHorasTrabajadasMes() {
		return horasTrabajadasMes;
	}

	public double getSueldoPorHora() {
		return sueldoPorHora;
	}

	/*
	 * retorna una cadena donde se concatena el significado y el valor de cada
	 * atributo en forma de lista.
	 */
	@Override
	public String obtenerDetalles() {
		Formateador formateo = new Formateador();
		return super.obtenerDetalles() + "\n" + "Horas trabajadas por mes: " + formateo.decimal(getHorasTrabajadasMes()) + "\n"
				+ "Sueldo por Hora: " + formateo.moneda(getSueldoPorHora());
		

	}

	/*
	 * Calcula el sueldo mensual del empleado con las horasTrabajadasMes y
	 * sueldoPorHora
	 * 
	 */
	@Override
	public double calcularSueldoMes() {
		return getHorasTrabajadasMes() * getSueldoPorHora();
	}

}
