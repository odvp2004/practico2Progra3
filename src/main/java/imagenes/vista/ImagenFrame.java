package imagenes.vista;

import imagenes.LectorDeImagen.AbrirImagen;
import imagenes.LectorDeImagen.GuardarImagen;
import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;


public class ImagenFrame extends JFrame {
    private Imagen modelo;
    private ImagenPanel centerPanel;
    private RightPanel rightPanel;
    private JMenu jMenu;
    private JMenuItem nuevoItem, guardarItem, salirItem;
    private JMenuBar jMenubar;
    private static final Logger logger = LogManager.getRootLogger();


    public ImagenFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        modelo = new Imagen("dino.jpg");

        rightPanel = new RightPanel(modelo);
        rightPanel.setPreferredSize(new Dimension(200, 0));

        centerPanel = new ImagenPanel(modelo, rightPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(rightPanel, BorderLayout.EAST);

        nuevoItem = new JMenuItem("Cargar Imagen");
        guardarItem = new JMenuItem("Guardar Imagen");
        salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(e -> {
            logger.info("Se ha cerrado el programa");
            System.exit(0);
        });
        nuevoItem.addActionListener(e -> {
            AbrirImagen abrirImagen = new AbrirImagen(this, modelo);
        });
        guardarItem.addActionListener(e -> {
            GuardarImagen guardarImagen = new GuardarImagen(this, modelo);
        });

        jMenu = new JMenu("Archivo");
        jMenu.add(nuevoItem);
        jMenu.add(guardarItem);
        jMenu.add(salirItem);
        jMenubar = new JMenuBar();
        jMenubar.add(jMenu);
        setJMenuBar(jMenubar);

        this.pack();
        this.setVisible(true);
    }


}
