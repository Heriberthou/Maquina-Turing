package moniTech.gui;

import javax.swing.*;

import moniTech.dominio.Monitor;
import moniTech.utilerias.MiFocusTraversalPolicy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class CatalogoMonitor extends JDialog implements ItemListener {

	// Serial para mantener compatibilidad en la serialización de la clase
	private static final long serialVersionUID = 1L;

	// Menú de operaciones
	private JMenu operaciones;
	private JMenuItem nuevoMenu;
	private JMenuItem modificarMenu;
	private JMenuItem guardarMenu;
	private JMenuItem eliminarMenu;
	private JMenuItem cancelarMenu;
	private JMenuBar menuBar;

	// Botones principales
	private JComboBox<Monitor> entidades;
	private JButton nuevo;
	private JButton modificar;
	private JButton guardar;
	private JButton eliminar;
	private JButton cancelar;

	// Campos para la información del monitor
	private JTextField añoFabricacion; // Año en que se fabricó el monitor
	private JSpinner precio; // Precio del monitor
	private JTextField modelo; // Modelo del monitor
	private JTextField numeroSerie; // Número de serie único del monitor
	private JTextField fechaLanzamiento; // Fecha de lanzamiento al mercado
	private ButtonGroup formaDePantala;
	private JRadioButton plano; // Opción de pantalla plana
	private JRadioButton curvo; // Opción de pantalla curva
	private JComboBox<String> marca; // Marca del monitor
	private JCheckBox vga; // Conector VGA
	private JCheckBox dvi; // Conector DVI
	private JCheckBox hdmi; // Conector HDMI
	private JComboBox<String> colorAgregar; // Lista de colores disponibles para agregar
	private JList<String> colorQuitar; // Lista de colores para quitar
	private JLabel imagen; // Imagen del monitor
	private JButton agregar; // Botón para agregar un color
	private JButton quitar; // Botón para quitar un color
	private JButton seleccionar; // Botón para seleccionar una imagen

	/**
	 * Constructor que inicializa la interfaz gráfica del catálogo de monitores.
	 */
	public CatalogoMonitor(JFrame principal) {
		super(principal, "Catálogo de monitores");
		// Configuración de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/moniTech/imagenes/icono.png")));
		this.setModal(true);
		// Inicialización de paneles
		this.setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		JPanel panelEste = new JPanel();
		JPanel panelSur = new JPanel();
		JPanel panelSurAuxOeste = new JPanel();
		JPanel panelSurAbajoOeste = new JPanel();
		JPanel panelSurAuxEste = new JPanel();
		JPanel panelAux = new JPanel();

		panelEste.setLayout(new GridLayout(5, 1));

		// --------------------NUEVO--------------------

		Action accionNuevo = new AbstractAction("Nuevo",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/alta.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionNuevo();
			}
		};

		accionNuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		accionNuevo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		accionNuevo.putValue(Action.SHORT_DESCRIPTION, "Crea una nueva operación");

		nuevoMenu = new JMenuItem(accionNuevo);

		nuevo = new JButton(accionNuevo);
		nuevo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionNuevo.getValue(Action.ACCELERATOR_KEY), "nuevo");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(nuevo);
		panelEste.add(panelAux);

		// -----------------MODIFICAR-------------------

		Action accionModificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/cambios.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionModificar();
			}
		};
		accionModificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		accionModificar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		accionModificar.putValue(Action.SHORT_DESCRIPTION, "Modifica la operación actual");

		modificarMenu = new JMenuItem(accionModificar);

		modificar = new JButton(accionModificar);
		modificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionModificar.getValue(Action.ACCELERATOR_KEY), "Modificar");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(modificar);
		panelEste.add(panelAux);

		// ----------------GUARDAR-----------------------
		Action accionGuardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/guardar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionGuardar();
			}
		};
		accionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		accionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		accionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarda la operación actual");

		guardarMenu = new JMenuItem(accionGuardar);

		guardar = new JButton(accionGuardar);
		guardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionGuardar.getValue(Action.ACCELERATOR_KEY), "guardar");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(guardar);
		panelEste.add(panelAux);

		// -----------------ELIMINAR-----------------------
		Action accionEliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/baja.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionEliminar();
			}
		};
		accionEliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		accionEliminar.putValue(Action.SHORT_DESCRIPTION, "Elimina la operación actual");

		eliminarMenu = new JMenuItem(accionEliminar);

		eliminar = new JButton(accionEliminar);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(eliminar);
		panelEste.add(panelAux);

		// ---------------CANCELAR--------------------------
		Action accionCancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/cancelar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionCancelar();
			}
		};
		accionCancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		accionCancelar.putValue(Action.SHORT_DESCRIPTION, "Cancela la operacion");

		cancelarMenu = new JMenuItem(accionCancelar);

		cancelar = new JButton(accionCancelar);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(cancelar);
		panelEste.add(panelAux);

		// -----------Menú Operaciones -----------------------.
		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/archivo.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);
		operaciones.setToolTipText(
				"Gestiona las operaciones del catálogo: crear, modificar, guardar, eliminar o cancelar.");

		operaciones.add(nuevoMenu);
		operaciones.add(modificarMenu);
		operaciones.add(guardarMenu);
		operaciones.add(eliminarMenu);
		operaciones.add(cancelarMenu);

		menuBar = new JMenuBar();
		menuBar.add(operaciones);
		this.setJMenuBar(menuBar);

		this.add(panelEste, BorderLayout.EAST);

		JLabel entidad = new JLabel("Monitores"); // Crea un JLabel que muestra "Monitores".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(entidad);
		panelNorte.add(panelAux);

		entidades = new JComboBox<Monitor>(); // Crea un JComboBox para seleccionar entidades.
		entidades.setPreferredSize(new Dimension(800, 20));
		entidad.setLabelFor(entidades);
		entidad.setDisplayedMnemonic(KeyEvent.VK_I);
		entidades.setToolTipText("Muestra un monitor ya existente");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());
		panelAux.add(entidades);
		panelNorte.add(panelAux);
		this.add(panelNorte, BorderLayout.NORTH);

		entidades.addItemListener(this);

		panelCentro.setLayout(new GridLayout(4, 4));

		JLabel muestraMarca = new JLabel("Marca "); // Crea un JLabel para mostrar "Marca".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraMarca);
		panelCentro.add(panelAux);

		marca = new JComboBox<>(); // Crea un JComboBox para la selección de marcas.
		marca.setPreferredSize(new Dimension(150, 20));
		marca.setEditable(true);
		muestraMarca.setLabelFor(marca);
		muestraMarca.setDisplayedMnemonic(KeyEvent.VK_A);
		marca.setToolTipText("Seleccione o ingrese la marca del monitor");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(marca);
		panelCentro.add(panelAux);

		JLabel muestraModelo = new JLabel("Modelo"); // Crea un JLabel para mostrar "Modelo".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraModelo);
		panelCentro.add(panelAux);

		modelo = new JTextField(); // Crea un JTextField para ingresar el modelo.
		modelo.setEditable(true);
		modelo.setPreferredSize(new Dimension(150, 20));
		muestraModelo.setLabelFor(modelo);
		muestraModelo.setDisplayedMnemonic(KeyEvent.VK_D);
		modelo.setToolTipText("Se ingresa el modelo del monitor");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(modelo);
		panelCentro.add(panelAux);

		JLabel muestraPrecio = new JLabel("Precio"); // Crea un JLabel para mostrar "Precio".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraPrecio);
		panelCentro.add(panelAux);

		precio = new JSpinner(new SpinnerNumberModel(3000, 2000, 80000, 100)); // Crea un JSpinner para seleccionar el
																				// precio
		precio.setPreferredSize(new Dimension(80, 20));
		muestraPrecio.setLabelFor(precio);
		muestraPrecio.setDisplayedMnemonic(KeyEvent.VK_P);
		precio.setToolTipText("Seleccione el precio del monitor");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(precio);
		panelCentro.add(panelAux);

		JLabel formaPantalla = new JLabel("Forma de la pantalla"); // Crea un JLabel para mostrar "Forma de la
																	// pantalla".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(formaPantalla);
		panelCentro.add(panelAux);

		plano = new JRadioButton("Plano", true); // Crea un botón de radio para "Plano" y lo selecciona por defecto.
		curvo = new JRadioButton("Curvo"); // Crea otro botón de radio para "Curvo".
		formaDePantala = new ButtonGroup(); // Crea un ButtonGroup para agrupar los botones de "plano" y
											// "curvo".
		formaDePantala.add(plano);
		formaDePantala.add(curvo);
		formaPantalla.setLabelFor(plano);
		formaPantalla.setDisplayedMnemonic(KeyEvent.VK_F);
		plano.setToolTipText("superficie del monitor plano");
		curvo.setToolTipText("superficie del monitor curvo");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(plano);
		panelAux.add(curvo);
		panelCentro.add(panelAux);

		JLabel muestraTipoPuerto = new JLabel("Tipos de puerto"); // Crea un JLabel para mostrar "Tipos de puerto".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraTipoPuerto);
		panelCentro.add(panelAux);

		vga = new JCheckBox("VGA", true);
		dvi = new JCheckBox("DVI");
		hdmi = new JCheckBox("HDMI");
		muestraTipoPuerto.setLabelFor(vga);
		muestraTipoPuerto.setDisplayedMnemonic(KeyEvent.VK_T);
		muestraTipoPuerto.setToolTipText("Tipos de conectores físicos para la transmisión de señales de video y audio");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(vga);
		panelAux.add(dvi);
		panelAux.add(hdmi);
		panelCentro.add(panelAux);

		JLabel muestraNumeroSerie = new JLabel("Número de Serie"); // Crea un JLabel para mostrar "Número de Serie".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraNumeroSerie);
		panelCentro.add(panelAux);

		numeroSerie = new JTextField();
		numeroSerie.setEditable(true);
		numeroSerie.setPreferredSize(new Dimension(150, 20));
		numeroSerie.setToolTipText("El formato del número de serie es:[3 números][4 letras Mayusculas][5 alfanuméricos]"
				+ "Ejemplo: 123ABCD123AB");
		muestraNumeroSerie.setLabelFor(numeroSerie);
		muestraNumeroSerie.setDisplayedMnemonic(KeyEvent.VK_S);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(numeroSerie);
		panelCentro.add(panelAux);

		JLabel muestraAñoFabricacion = new JLabel("Año de fabricación"); // Crea un JLabel para mostrar "Año de
																			// fabricación"
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraAñoFabricacion);
		panelCentro.add(panelAux);

		añoFabricacion = new JTextField(); // Crea un JTextField para ingresar el año de fabricación.
		añoFabricacion.setEditable(true);
		añoFabricacion.setPreferredSize(new Dimension(150, 20));
		muestraAñoFabricacion.setLabelFor(añoFabricacion);
		muestraAñoFabricacion.setDisplayedMnemonic(KeyEvent.VK_B);
		añoFabricacion.setToolTipText("Año en el que se fabricó el monitor");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(añoFabricacion);
		panelCentro.add(panelAux);

		JLabel muestrafechaLanzamiento = new JLabel("Fecha de lanzamiento"); // Crea un JLabel para mostrar "Fecha de
																				// lanzamiento".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestrafechaLanzamiento);
		panelCentro.add(panelAux);

		fechaLanzamiento = new JTextField(); // Crea un JTextField para ingresar la fecha de lanzamiento.
		fechaLanzamiento.setEditable(true);
		fechaLanzamiento.setPreferredSize(new Dimension(150, 20));
		muestrafechaLanzamiento.setLabelFor(fechaLanzamiento);
		muestrafechaLanzamiento.setDisplayedMnemonic(KeyEvent.VK_H);
		fechaLanzamiento.setToolTipText("Año en el que se lanzo el monitor al mercado");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(fechaLanzamiento);
		panelCentro.add(panelAux);

		this.add(panelCentro, BorderLayout.CENTER);

		panelSur.setLayout(new GridLayout(1, 2));
		panelSur.setPreferredSize(new Dimension(250, 200));

		panelSurAuxOeste.setLayout(new GridLayout(2, 1));

		JLabel muestrafColor = new JLabel("Color"); // Crea un JLabel para mostrar "Color".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(muestrafColor);
		panelSurAuxOeste.add(panelAux);

		panelSur.add(panelSurAuxOeste);

		panelSurAbajoOeste.setLayout(new GridLayout(2, 2));

		colorAgregar = new JComboBox<String>(); // Crea un JComboBox para seleccionar colores.
		colorAgregar.setPreferredSize(new Dimension(150, 20));
		colorAgregar.setEditable(true);
		muestrafColor.setLabelFor(colorAgregar);
		muestrafColor.setDisplayedMnemonic(KeyEvent.VK_L);
		colorAgregar.setToolTipText("Se agrega un color");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(colorAgregar);
		panelSurAbajoOeste.add(panelAux);

		// ---------------AGREGAR--------------------------

		Action accionAgregar = new AbstractAction("Agregar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/agregar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionAgregar();
			}
		};
		accionAgregar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
		accionAgregar.putValue(Action.SHORT_DESCRIPTION, "Agrega un nuevo color seleccionado");

		agregar = new JButton(accionAgregar);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(agregar);
		panelSurAbajoOeste.add(panelAux);

		colorQuitar = new JList<>(new DefaultListModel<>());
		JScrollPane scrollPane = new JScrollPane(colorQuitar);
		scrollPane.setPreferredSize(new Dimension(150, 80));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		muestrafColor.setLabelFor(colorAgregar);
		muestrafColor.setDisplayedMnemonic(KeyEvent.VK_L);
		colorQuitar.setToolTipText("Se muestra la lista de colores actuales");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(scrollPane);
		panelSurAbajoOeste.add(panelAux);

		// ---------------QUITAR--------------------------

		Action accionQuitar = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/baja.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionQuitar();
			}
		};
		accionQuitar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
		accionQuitar.putValue(Action.SHORT_DESCRIPTION, "Quita el elemento seleccionado");

		quitar = new JButton(accionQuitar);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(quitar);
		panelSurAbajoOeste.add(panelAux);

		panelSur.add(panelSurAbajoOeste);

		panelSurAuxEste.setLayout(new GridLayout(1, 3));
		panelSurAuxEste.setPreferredSize(new Dimension(250, -1));

		JLabel muestraImagen = new JLabel("Imagen");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(muestraImagen);
		panelSurAuxEste.add(panelAux);

		imagen = new JLabel();
		muestraImagen.setLabelFor(imagen);
		// muestraImagen.setDisplayedMnemonic(KeyEvent.VK_I);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(imagen);
		panelSurAuxEste.add(panelAux);

		// ---------------SELECCIONAR--------------------------

		Action accionSeleccionar = new AbstractAction("Seleccionar",
				new ImageIcon(getClass().getResource("/moniTech/imagenes/escoger.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionSeleccionar();
			}
		};
		accionSeleccionar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
		accionSeleccionar.putValue(Action.SHORT_DESCRIPTION, "Selecciona una imagen");

		seleccionar = new JButton(accionSeleccionar);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(seleccionar);
		panelSurAuxEste.add(panelAux);

		panelSur.add(panelSurAuxEste);

		this.add(panelSur, BorderLayout.SOUTH);
		this.setSize(new Dimension(1100, 700)); // Tamaño de la ventana
		this.setLocationRelativeTo(principal); // Centrar la ventana
		this.setResizable(false); // Desactivar redimensionado
		inicializar();
		establecerPoliticaFoco();// Método para configurar el orden de foco
		this.setVisible(true); // Hacer visible la ventana
	}

	private void inicializar() {
		habilitarCampos(false);
		// JButton
		nuevo.setEnabled(true);
		guardar.setEnabled(false);
		cancelar.setEnabled(false);
		// JMenuItem
		nuevoMenu.setEnabled(true);
		guardarMenu.setEnabled(false);
		cancelarMenu.setEnabled(false);

		verificarLista();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(entidades)) {
			mostrarEntidad();
		}
	}

	/**
	 * Método que gestiona la acción de crear un nuevo monitor.
	 */
	private void accionNuevo() {
		habilitarCampos(true);
		limpiarCampos();
		// JButton
		nuevo.setEnabled(false);
		guardar.setEnabled(true);
		cancelar.setEnabled(true);
		modificar.setEnabled(false);
		eliminar.setEnabled(false);
		// JMenuItem
		nuevoMenu.setEnabled(false);
		guardarMenu.setEnabled(true);
		cancelarMenu.setEnabled(true);
		modificarMenu.setEnabled(false);
		eliminarMenu.setEnabled(false);
		// lista principal
		entidades.setEnabled(false);

		// JOptionPane.showMessageDialog(this, "Nuevo", "Nuevo",
		// JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de modificar un monitor existente.
	 */
	private void accionModificar() {
		JOptionPane.showMessageDialog(this, "Modificar", "Modificar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de guardar los cambios de un monitor.
	 */
	private void accionGuardar() {
		/*
		 * número libre (añoFabricacion), número con rango (precio), texto en formato
		 * libre (modelo), texto con formato predefinido (numeroSerie) dato obtenido de
		 * las opciones mutuamente excluyentes fijas. (formaPantalla)
		 */

		Monitor monitor = new Monitor();

		monitor.setAñoFabricacion(añoFabricacion.getText());
		monitor.setPrecio(String.valueOf(precio.getValue()));
		monitor.setModelo(modelo.getText());
		monitor.setNumeroSerie(numeroSerie.getText());

		String formaDePantalla;
		if (plano.isSelected()) {
			formaDePantalla = plano.getText();
		} else {
			formaDePantalla = curvo.getText();
		}
		monitor.setFormaPantalla(formaDePantalla);
		entidades.addItem(monitor);

		JOptionPane.showMessageDialog(this, "Guardado exitoso", "Guardar", JOptionPane.INFORMATION_MESSAGE);

		accionCancelar();
	}

	/**
	 * Método que gestiona la acción de eliminar un monitor.
	 */
	private void accionEliminar() {
		JOptionPane.showMessageDialog(this, "Eliminar", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de cancelar la operación actual.
	 */
	private void accionCancelar() {
		habilitarCampos(false);
		limpiarCampos();

		// JButton
		nuevo.setEnabled(true);
		guardar.setEnabled(false);
		cancelar.setEnabled(false);
		// JMenuItem
		nuevoMenu.setEnabled(true);
		guardarMenu.setEnabled(false);
		cancelarMenu.setEnabled(false);

		verificarLista();
		// JOptionPane.showMessageDialog(this, "Cancelar", "Cancelar",
		// JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de agregar un nuevo color.
	 */
	private void accionAgregar() {
		JOptionPane.showMessageDialog(this, "Agregar", "Agregar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de quitar un color existente.
	 */
	private void accionQuitar() {
		JOptionPane.showMessageDialog(this, "Quitar", "Quitar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que gestiona la acción de seleccionar una imagen.
	 */
	private void accionSeleccionar() {
		JOptionPane.showMessageDialog(this, "Seleccionar", "Seleccionar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que establece la política de foco para los componentes en la ventana.
	 */

	private void establecerPoliticaFoco() {
		Vector<Component> componentes = new Vector<Component>();

		componentes.add(nuevo);
		componentes.add(modificar);
		componentes.add(guardar);
		componentes.add(eliminar);
		componentes.add(cancelar);
		componentes.add(entidades);
		// componentes.add(marca);
		componentes.add(modelo);
		componentes.add(precio);
		componentes.add(plano);
		componentes.add(curvo);
		componentes.add(vga);
		componentes.add(dvi);
		componentes.add(hdmi);
		componentes.add(numeroSerie);
		componentes.add(añoFabricacion);
		componentes.add(fechaLanzamiento);
		componentes.add(agregar);
		componentes.add(quitar);
		componentes.add(seleccionar);

		MiFocusTraversalPolicy politicaFoco = new MiFocusTraversalPolicy(componentes);
		this.setFocusTraversalPolicy(politicaFoco);
	}

	/**
	 * Método que limpia el contenido de los campos de texto y selecciona el valor
	 * por defecto de los radios.
	 */
	private void limpiarCampos() {
		añoFabricacion.setText("");
		modelo.setText("");
		numeroSerie.setText("");
		fechaLanzamiento.setText("");
		plano.setSelected(true);
	}

	/**
	 * habilitará o deshabilitará la escritura de los campos de texto y habilitará o
	 * deshabilitará al resto de los componentes gráficos.
	 * 
	 * @param habilitar
	 */
	private void habilitarCampos(boolean habilitar) {
		// JTextField
		añoFabricacion.setEnabled(habilitar);
		modelo.setEnabled(habilitar);
		numeroSerie.setEnabled(habilitar);
		fechaLanzamiento.setEnabled(habilitar);
		// JSpinner
		precio.setEnabled(habilitar);
		// JRadionButton
		plano.setEnabled(habilitar);
		curvo.setEnabled(habilitar);
		// JcomboBox
		marca.setEnabled(habilitar);
		colorAgregar.setEnabled(habilitar);
		// JCheckBox
		vga.setEnabled(habilitar);
		dvi.setEnabled(habilitar);
		hdmi.setEnabled(habilitar);
		// JList
		colorQuitar.setEnabled(habilitar);
		// JLabel
		imagen.setEnabled(habilitar);
		// JButton
		agregar.setEnabled(habilitar);
		quitar.setEnabled(habilitar);
		seleccionar.setEnabled(habilitar);
	}

	/**
	 * verificará si la lista desplegableprincipal tiene elementos. Si los tiene,
	 * habilitará a los botones y menús “Modificar” y “Eliminar”, y a la propia
	 * lista desplegable principal.
	 */
	private void verificarLista() {
		boolean verificar;
		if (entidades.getItemCount() > 0) {
			verificar = true;
		} else {
			verificar = false;
		}
		modificarMenu.setEnabled(verificar);
		modificar.setEnabled(verificar);
		eliminarMenu.setEnabled(verificar);
		eliminar.setEnabled(verificar);
		entidades.setEnabled(verificar);
	}

	/**
	 * Obtiene el objeto seleccionado de la lista desplegable y muestra sus valores
	 * en los componentes gráficos.
	 */
	private void mostrarEntidad() {
		Monitor seleccionado = (Monitor) entidades.getSelectedItem();

		añoFabricacion.setText(String.valueOf(seleccionado.getAñoFabricacion()));
		precio.setValue(seleccionado.getPrecio());
		modelo.setText(seleccionado.getModelo());
		numeroSerie.setText(seleccionado.getNumeroSerie());
		if ("curvo".equalsIgnoreCase(seleccionado.getFormaPantalla())) {
			curvo.setSelected(true);
		} else {
			plano.setSelected(true);
		}
	}
}
