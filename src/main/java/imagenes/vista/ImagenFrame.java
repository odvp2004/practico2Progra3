package imagenes.vista;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class ImagenFrame extends JFrame {
    private Imagen modelo;
    private ImagenPanel centerPanel;
    private RightPanel rightPanel;
    private JMenu jMenu;
    private JMenuItem nuevoItem, guardarItem, salirItem;
    private JMenuBar jMenubar;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filtro;
    private static final Logger logger = LogManager.getRootLogger();
    private BufferedImage imagenActual;
    private final String DIRECTORIO_RECURSOS = "D:\\NUR\\3er Semestre\\pr2v2\\src\\main\\java\\imagenes\\ejemplos";
    private final String DIRECTORIO_DESTINO = "D:\\nuevasImagenes\\";

    public ImagenFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(DIRECTORIO_RECURSOS));
        filtro = new FileNameExtensionFilter("Archivos de Imagen validos", "jpg", "png");
        fileChooser.setFileFilter(filtro);
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
            abrirImagen();
        });
        guardarItem.addActionListener(e -> {
            guardarImagen();
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

    private void abrirImagen() {
        fileChooser.setDialogTitle("Abrir Imagen");
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            try {
                imagenActual = ImageIO.read(archivoSeleccionado);
                modelo.setImagen(imagenActual);
                logger.info("La imagen se obtuvo correctamente para ser abierta");
                this.pack();
            } catch (IOException ex) {
                logger.error("No se pudo cargar la imagen");
                JOptionPane.showMessageDialog(this, "Error al abrir la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarImagen() {
        BufferedImage imagen = modelo.getImagen();
        try {
            for (int x = 0; x < modelo.getWidth(); x++) {
                for (int y = 0; y < modelo.getHeight(); y++) {
                    // Establecer el color del píxel
                    imagen.setRGB(x, y, modelo.getPixeles()[x][y]);
                }
            }
            String nombreImagen = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo:", "Guardar Imagen", JOptionPane.PLAIN_MESSAGE);
            File archivoSalida = new File(DIRECTORIO_DESTINO + nombreImagen + ".jpeg");
            ImageIO.write(imagen, "jpeg", archivoSalida);
            logger.info("Imagen guardada en: " + DIRECTORIO_DESTINO);

        } catch (IOException e) {
            logger.error("No se pudo guardar la imagen");
        }
    }
}
