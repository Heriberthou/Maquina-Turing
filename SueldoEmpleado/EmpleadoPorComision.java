

public class EmpleadoPorComision extends Empleado {
	private float porcentajeTasaComision;
	private double ventaMensual;

	public EmpleadoPorComision(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String porcentajeTasaComision, String ventaMensual) {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);
		setPorcentajeTasaComision(porcentajeTasaComision);
		setVentaMensual(ventaMensual);
	}

	public void setPorcentajeTasaComision(String porcentajeTasaComision) {
		setPorcentajeTasaComision(Float.parseFloat(porcentajeTasaComision));
	}

	public void setPorcentajeTasaComision(float porcentajeTasaComision) {
		this.porcentajeTasaComision = porcentajeTasaComision;
	}

	public void setVentaMensual(String ventaMensual) {
		setVentaMensual(Double.parseDouble(ventaMensual));
	}

	public void setVentaMensual(double ventaMensual) {
		this.ventaMensual = ventaMensual;
	}

	public float getPorcentajeTasaComision() {
		return porcentajeTasaComision;
	}

	public double getVentaMensual() {
		return ventaMensual;
	}

	/*
	 * retorna una cadena donde se concatena el significado y el valor de cada
	 * atributo en forma de lista.
	 */
	@Override
	public String obtenerDetalles() {
		Formateador formateo = new Formateador();
		return super.obtenerDetalles() + "\n" + "Porcentaje de tasa comisión: %" + formateo.decimal(getPorcentajeTasaComision()) + "\n"
				+ "Venta mensual: " + formateo.moneda(getVentaMensual());
	}

	/*
	 * Calcula el sueldo mensual del empleado con la ventaMensual y
	 * PorcentajeTasaComision
	 */
	@Override
	public double calcularSueldoMes() {

		return (getVentaMensual() * getPorcentajeTasaComision()) / 100;
	}

}
