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

	// Serial para mantener compatibilidad en la serializaci�n de la clase
	private static final long serialVersionUID = 1L;

	// Men� de operaciones
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

	// Campos para la informaci�n del monitor
	private JTextField a�oFabricacion; // A�o en que se fabric� el monitor
	private JSpinner precio; // Precio del monitor
	private JTextField modelo; // Modelo del monitor
	private JTextField numeroSerie; // N�mero de serie �nico del monitor
	private JTextField fechaLanzamiento; // Fecha de lanzamiento al mercado
	private ButtonGroup formaDePantala;
	private JRadioButton plano; // Opci�n de pantalla plana
	private JRadioButton curvo; // Opci�n de pantalla curva
	private JComboBox<String> marca; // Marca del monitor
	private JCheckBox vga; // Conector VGA
	private JCheckBox dvi; // Conector DVI
	private JCheckBox hdmi; // Conector HDMI
	private JComboBox<String> colorAgregar; // Lista de colores disponibles para agregar
	private JList<String> colorQuitar; // Lista de colores para quitar
	private JLabel imagen; // Imagen del monitor
	private JButton agregar; // Bot�n para agregar un color
	private JButton quitar; // Bot�n para quitar un color
	private JButton seleccionar; // Bot�n para seleccionar una imagen

	/**
	 * Constructor que inicializa la interfaz gr�fica del cat�logo de monitores.
	 */
	public CatalogoMonitor(JFrame principal) {
		super(principal, "Cat�logo de monitores");
		// Configuraci�n de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/moniTech/imagenes/icono.png")));
		this.setModal(true);
		// Inicializaci�n de paneles
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
		accionNuevo.putValue(Action.SHORT_DESCRIPTION, "Crea una nueva operaci�n");

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
		accionModificar.putValue(Action.SHORT_DESCRIPTION, "Modifica la operaci�n actual");

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
		accionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarda la operaci�n actual");

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
		accionEliminar.putValue(Action.SHORT_DESCRIPTION, "Elimina la operaci�n actual");

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

		// -----------Men� Operaciones -----------------------.
		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/archivo.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);
		operaciones.setToolTipText(
				"Gestiona las operaciones del cat�logo: crear, modificar, guardar, eliminar o cancelar.");

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

		marca = new JComboBox<>(); // Crea un JComboBox para la selecci�n de marcas.
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

		plano = new JRadioButton("Plano", true); // Crea un bot�n de radio para "Plano" y lo selecciona por defecto.
		curvo = new JRadioButton("Curvo"); // Crea otro bot�n de radio para "Curvo".
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
		muestraTipoPuerto.setToolTipText("Tipos de conectores f�sicos para la transmisi�n de se�ales de video y audio");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(vga);
		panelAux.add(dvi);
		panelAux.add(hdmi);
		panelCentro.add(panelAux);

		JLabel muestraNumeroSerie = new JLabel("N�mero de Serie"); // Crea un JLabel para mostrar "N�mero de Serie".
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraNumeroSerie);
		panelCentro.add(panelAux);

		numeroSerie = new JTextField();
		numeroSerie.setEditable(true);
		numeroSerie.setPreferredSize(new Dimension(150, 20));
		numeroSerie.setToolTipText("El formato del n�mero de serie es:[3 n�meros][4 letras Mayusculas][5 alfanum�ricos]"
				+ "Ejemplo: 123ABCD123AB");
		muestraNumeroSerie.setLabelFor(numeroSerie);
		muestraNumeroSerie.setDisplayedMnemonic(KeyEvent.VK_S);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(numeroSerie);
		panelCentro.add(panelAux);

		JLabel muestraA�oFabricacion = new JLabel("A�o de fabricaci�n"); // Crea un JLabel para mostrar "A�o de
																			// fabricaci�n"
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(muestraA�oFabricacion);
		panelCentro.add(panelAux);

		a�oFabricacion = new JTextField(); // Crea un JTextField para ingresar el a�o de fabricaci�n.
		a�oFabricacion.setEditable(true);
		a�oFabricacion.setPreferredSize(new Dimension(150, 20));
		muestraA�oFabricacion.setLabelFor(a�oFabricacion);
		muestraA�oFabricacion.setDisplayedMnemonic(KeyEvent.VK_B);
		a�oFabricacion.setToolTipText("A�o en el que se fabric� el monitor");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(a�oFabricacion);
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
		fechaLanzamiento.setToolTipText("A�o en el que se lanzo el monitor al mercado");
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
		this.setSize(new Dimension(1100, 700)); // Tama�o de la ventana
		this.setLocationRelativeTo(principal); // Centrar la ventana
		this.setResizable(false); // Desactivar redimensionado
		inicializar();
		establecerPoliticaFoco();// M�todo para configurar el orden de foco
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
	 * M�todo que gestiona la acci�n de crear un nuevo monitor.
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
	 * M�todo que gestiona la acci�n de modificar un monitor existente.
	 */
	private void accionModificar() {
		JOptionPane.showMessageDialog(this, "Modificar", "Modificar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * M�todo que gestiona la acci�n de guardar los cambios de un monitor.
	 */
	private void accionGuardar() {
		/*
		 * n�mero libre (a�oFabricacion), n�mero con rango (precio), texto en formato
		 * libre (modelo), texto con formato predefinido (numeroSerie) dato obtenido de
		 * las opciones mutuamente excluyentes fijas. (formaPantalla)
		 */

		Monitor monitor = new Monitor();

		monitor.setA�oFabricacion(a�oFabricacion.getText());
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
	 * M�todo que gestiona la acci�n de eliminar un monitor.
	 */
	private void accionEliminar() {
		JOptionPane.showMessageDialog(this, "Eliminar", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * M�todo que gestiona la acci�n de cancelar la operaci�n actual.
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
	 * M�todo que gestiona la acci�n de agregar un nuevo color.
	 */
	private void accionAgregar() {
		JOptionPane.showMessageDialog(this, "Agregar", "Agregar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * M�todo que gestiona la acci�n de quitar un color existente.
	 */
	private void accionQuitar() {
		JOptionPane.showMessageDialog(this, "Quitar", "Quitar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * M�todo que gestiona la acci�n de seleccionar una imagen.
	 */
	private void accionSeleccionar() {
		JOptionPane.showMessageDialog(this, "Seleccionar", "Seleccionar", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * M�todo que establece la pol�tica de foco para los componentes en la ventana.
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
		componentes.add(a�oFabricacion);
		componentes.add(fechaLanzamiento);
		componentes.add(agregar);
		componentes.add(quitar);
		componentes.add(seleccionar);

		MiFocusTraversalPolicy politicaFoco = new MiFocusTraversalPolicy(componentes);
		this.setFocusTraversalPolicy(politicaFoco);
	}

	/**
	 * M�todo que limpia el contenido de los campos de texto y selecciona el valor
	 * por defecto de los radios.
	 */
	private void limpiarCampos() {
		a�oFabricacion.setText("");
		modelo.setText("");
		numeroSerie.setText("");
		fechaLanzamiento.setText("");
		plano.setSelected(true);
	}

	/**
	 * habilitar� o deshabilitar� la escritura de los campos de texto y habilitar� o
	 * deshabilitar� al resto de los componentes gr�ficos.
	 * 
	 * @param habilitar
	 */
	private void habilitarCampos(boolean habilitar) {
		// JTextField
		a�oFabricacion.setEnabled(habilitar);
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
	 * verificar� si la lista desplegableprincipal tiene elementos. Si los tiene,
	 * habilitar� a los botones y men�s �Modificar� y �Eliminar�, y a la propia
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
	 * en los componentes gr�ficos.
	 */
	private void mostrarEntidad() {
		Monitor seleccionado = (Monitor) entidades.getSelectedItem();

		a�oFabricacion.setText(String.valueOf(seleccionado.getA�oFabricacion()));
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
