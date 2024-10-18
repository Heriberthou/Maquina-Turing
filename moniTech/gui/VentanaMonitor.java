/*Autor: Heriberto Gómez Bolaina
 * Aplicacion: mi monitor
 * fecha: 20/06/2024
 */

package moniTech.gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMonitor extends JFrame {

    private static final long serialVersionUID = 1L;

    private JMenu archivoMenu;
    private JMenu operacionesMenu;
    private JMenu ayudaMenu;
    private JMenuItem abrirMenu;
    private JMenuItem guardarMenu;
    private JMenuItem salirMenu;
    private JMenuItem catalogoMenu;
    private JMenuItem consultaMenu;
    private JMenuItem acercaDeMenu;
    private JMenuBar menuBar;

    public VentanaMonitor() {
        super("MoniTech");

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/moniTech/imagenes/icono.png")));

        // menú Archivo
        archivoMenu = new JMenu("Archivo");
        archivoMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/archivo.png")));
        archivoMenu.setMnemonic(KeyEvent.VK_A);
        archivoMenu.setToolTipText("Gestiona las opciones de archivo, como abrir y guardar.");

        // menú Abrir
        abrirMenu = new JMenuItem("Abrir");
        abrirMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/abrir.png")));
        abrirMenu.setMnemonic(KeyEvent.VK_A);
        abrirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        abrirMenu.setToolTipText("Abre un archivo");
        abrirMenu.addActionListener(new ManejoEventos());

        // menú Guardar
        guardarMenu = new JMenuItem("Guardar");
        guardarMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/guardar.png")));
        guardarMenu.setMnemonic(KeyEvent.VK_G);
        guardarMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        guardarMenu.setToolTipText("Guarda el archivo actual");
        guardarMenu.addActionListener(new ManejoEventos());

        // menú Salir
        salirMenu = new JMenuItem("Salir");
        salirMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/salir.png")));
        salirMenu.setMnemonic(KeyEvent.VK_S);
        salirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        salirMenu.setToolTipText("Cierra la aplicación");
        salirMenu.addActionListener(new ManejoEventos());

        // menú Archivo
        archivoMenu.add(abrirMenu);
        archivoMenu.add(guardarMenu);
        archivoMenu.addSeparator();
        archivoMenu.add(salirMenu);

        // menú Operaciones
        operacionesMenu = new JMenu("Operaciones");
        operacionesMenu.setMnemonic(KeyEvent.VK_O);
        operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/operaciones.png")));
        operacionesMenu.setToolTipText("Operaciones disponibles");

        // menú Catálogo
        catalogoMenu = new JMenuItem("Catálogo");
        catalogoMenu.setMnemonic(KeyEvent.VK_C);
        catalogoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        catalogoMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/catalogo.png")));
        catalogoMenu.setToolTipText("Accede al catálogo");
        catalogoMenu.addActionListener(new ManejoEventos());

        // menú Consultar
        consultaMenu = new JMenuItem("Consultar");
        consultaMenu.setMnemonic(KeyEvent.VK_S);
        consultaMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK));
        consultaMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/consultar.png")));
        consultaMenu.setToolTipText("Consulta información");
        consultaMenu.addActionListener(new ManejoEventos());

        // menú Operaciones
        operacionesMenu.add(catalogoMenu);
        operacionesMenu.addSeparator();
        operacionesMenu.add(consultaMenu);

        // menú Ayuda
        ayudaMenu = new JMenu("Ayuda");
        ayudaMenu.setMnemonic(KeyEvent.VK_Y);
        ayudaMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/ayuda.png")));
        ayudaMenu.setToolTipText("Accede a las opciones de ayuda");

        // menú Acerca de...
        acercaDeMenu = new JMenuItem("Acerca de...");
        acercaDeMenu.setMnemonic(KeyEvent.VK_C);
        acercaDeMenu.setIcon(new ImageIcon(getClass().getResource("/moniTech/imagenes/acercaDe.png")));
        acercaDeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
        acercaDeMenu.setToolTipText("Muestra los créditos del sistema");
        acercaDeMenu.addActionListener(new ManejoEventos());

        // menú Ayuda
        ayudaMenu.add(acercaDeMenu);

        menuBar = new JMenuBar();
        menuBar.add(archivoMenu);
        menuBar.add(operacionesMenu);
        menuBar.add(ayudaMenu);
        setJMenuBar(menuBar);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.getContentPane().setLayout(new FlowLayout());
        JLabel fondo = new JLabel();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/moniTech/imagenes/monitores.jpg"));
        Image imagenEscalada = imagenFondo.getImage().getScaledInstance(-1, getSize().height - 80, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(imagenEscalada));
        this.getContentPane().add(fondo);
        this.getContentPane().setBackground(Color.BLACK);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class ManejoEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(abrirMenu)) {
                abrir();
            } else if (e.getSource().equals(guardarMenu)) {
                guardar();
            } else if (e.getSource().equals(salirMenu)) {
                salir();
            } else if (e.getSource().equals(catalogoMenu)) {
            	mostrarDialogo();
            } else if (e.getSource().equals(consultaMenu)) {
                consultar();
            } else if (e.getSource().equals(acercaDeMenu)) {
                acercaDe();
            }
        }
    }
    private void mostrarDialogo() {
    	new CatalogoMonitor(VentanaMonitor.this);
    }
    
    private void abrir() {
        // Implementar lógica para abrir archivo
    }

    private void guardar() {
        // Implementar lógica para guardar archivo
    }

    private void salir() {
        System.exit(0);
    }

    private void consultar() {
        // Implementar lógica para consultar información
    }

    private void acercaDe() {
        JOptionPane.showMessageDialog(this, "MoniTech" + "\n\n"
                        + "Realizada por:"
                        + "\nHeriberto Gómez Bolaina" + "\n\n"
                        + "Derechos reservados UMAR " + '\u00A9' + " 2024",
                "Acerca de... MoniTech", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(getClass().getResource("/moniTech/imagenes/logo.png")));
    }
}
