
/*
 * Clase Principal 
 * Se ingresan los datos de los diferentes empleados mediante mensajes, al final se muestran sus datos ingresados�y saldo mensual.
 * Heriberto Gomez Bolaina
 * 09/10/2024
 */
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class MiEmpleado {
	public static void main(String[] args) {

		ArrayList<Empleado> empleados = new ArrayList<>();

		do {
			String[] opciones = { "Empleado por d�as", "Empleado por horas", "Empleado por comisi�n",
					"Empleado por d�as m�s comisi�n" };
			String empleado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de empleado: ",
					"Tipo de empleado", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			if (empleado == null) {
				continue;
			}
			// Datos generales de cada empleado.

			String numeroEmpleado = JOptionPane.showInputDialog(null, "Escriba el n�mero del empleado",
					"Datos generales", JOptionPane.QUESTION_MESSAGE);
			if (numeroEmpleado == null) {
				continue;
			}
			String curp = JOptionPane.showInputDialog(null, "Escriba la curp del empleado", "Datos generales",
					JOptionPane.QUESTION_MESSAGE);
			if (curp == null) {
				continue;
			}
			String primerNombre = JOptionPane.showInputDialog(null, "Escriba el primer nombre del empleado",
					"Datos generales", JOptionPane.QUESTION_MESSAGE);
			if (primerNombre == null) {
				continue;
			}
			String segundoNombre = JOptionPane.showInputDialog(null, "Escriba el segundo nombre del empleado",
					"Datos generales", JOptionPane.QUESTION_MESSAGE);
			if (segundoNombre == null) {
				continue;
			}
			String apellidoPaterno = JOptionPane.showInputDialog(null, "Escriba el apellido paterno del empleado",
					"Datos generales", JOptionPane.QUESTION_MESSAGE);
			if (apellidoPaterno == null) {
				continue;
			}
			String apellidoMaterno = JOptionPane.showInputDialog(null, "Escriba el apellido materno del empleado",
					"Datos generales", JOptionPane.QUESTION_MESSAGE);
			if (apellidoMaterno == null) {
				continue;
			}

			// Datos particulares de cada empleado

			// Empleado por horas
			if (opciones[2].equalsIgnoreCase(empleado)) {
				String sueldoPorHora = JOptionPane.showInputDialog(null, "Escriba el sueldo por hora del empleado",
						empleado, JOptionPane.QUESTION_MESSAGE);
				if (sueldoPorHora == null) {
					continue;
				}
				String horasTrabajadas = JOptionPane.showInputDialog(null,
						"Escriba las horas trabajados del mes del empleado", empleado, JOptionPane.QUESTION_MESSAGE);
				if (horasTrabajadas == null) {
					continue;
				}
				empleados.add(new EmpleadoPorHoras(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno,
						apellidoMaterno, horasTrabajadas, sueldoPorHora));
			}

			String sueldoDiario = null;
			String diasTrabajados = null;
			// Empleado por d�as y por d�as m�s comisi�n
			if ((opciones[0].equalsIgnoreCase(empleado))
					|| (opciones[3].equalsIgnoreCase(empleado))) {
				sueldoDiario = JOptionPane.showInputDialog(null, "Escriba el sueldo diario del empleado", empleado,
						JOptionPane.QUESTION_MESSAGE);
				if (sueldoDiario == null) {
					continue;
				}
				diasTrabajados = JOptionPane.showInputDialog(null, "Escriba los d�as trabajados del mes del empleado",
						empleado, JOptionPane.QUESTION_MESSAGE);
				if (diasTrabajados == null) {
					continue;
				}
				if ("Empleado por d�as".equalsIgnoreCase(empleado)) {
					empleados.add(new EmpleadoPorDias(numeroEmpleado, curp, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, sueldoDiario, diasTrabajados));
				}
			}
			// Empleado por comisi�n y por d�as m�s comisi�n
			if ((opciones[2].equalsIgnoreCase(empleado))
					|| opciones[3].equalsIgnoreCase(empleado)) {
				String porcentajeComision = JOptionPane.showInputDialog(null,
						"Escriba el porcentaje de comisi�n del empleado", empleado, JOptionPane.QUESTION_MESSAGE);
				if (porcentajeComision == null) {
					continue;
				}
				String ventaMensual = JOptionPane.showInputDialog(null, "Escriba las ventas del mes del empleado",
						empleado, JOptionPane.QUESTION_MESSAGE);
				if (ventaMensual == null) {
					continue;
				}
				if ("Empleado por comisi�n".equalsIgnoreCase(empleado)) {
					empleados.add(new EmpleadoPorComision(numeroEmpleado, curp, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, porcentajeComision, ventaMensual));
				} else {
					empleados.add(new EmpleadoPorDiasMasComision(numeroEmpleado, curp, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, porcentajeComision, ventaMensual, sueldoDiario,
							diasTrabajados));
				}
			}
		} while (JOptionPane.showConfirmDialog(null, "�Desea registrar un nuevo empleado?", "Registro de empleados",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

		String[] ordenaciones = { "Por nombres", "Por apellidos", "Por n�mero de empleado"};
		String orden = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de ordenaci�n: ",
				"Tipo de ordenaci�n", JOptionPane.QUESTION_MESSAGE, null, ordenaciones, ordenaciones[0]);
		
		
		// asigna el tipo de orden a cada empleado
		if (orden != null) {
			if (orden.equals("Por nombres")) {
				Empleado.setTipoOrdenacion(Empleado.POR_NOMBRES);
			} else if (orden.equals("Por apellidos")) {
				Empleado.setTipoOrdenacion(Empleado.POR_APELLIDOS);
			} else if (orden.equals("Por n�mero de empleado")) {
				Empleado.setTipoOrdenacion(Empleado.POR_NUMERO_EMPLEADO);
			}
		}
		
        // Ordenar empleados
        Collections.sort(empleados);
        
		// muestra los detalles de cada empleado registrado
		for (Empleado emp : empleados) {
						
			JOptionPane.showMessageDialog(null,
					emp.obtenerDetalles() + "\n\n" + "Sueldo al mes: " + emp.getSueldoMes(),
					"Empleado " + emp.getNumeroEmpleado(), JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
