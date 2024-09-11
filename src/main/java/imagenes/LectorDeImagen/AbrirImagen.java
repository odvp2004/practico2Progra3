package imagenes.LectorDeImagen;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AbrirImagen {
    private static final Logger logger = LogManager.getRootLogger();

    public AbrirImagen(JFrame parent, Imagen modelo) {
        JFileChooser fileChooser = new JFileChooser();
        String DIRECTORIO_RECURSOS = "D:\\NUR\\3er Semestre\\pr2v2\\src\\main\\java\\imagenes\\ejemplos";
        fileChooser.setCurrentDirectory(new File(DIRECTORIO_RECURSOS));

        fileChooser.setDialogTitle("Abrir Imagen");
        int resultado = fileChooser.showOpenDialog(parent);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            try {
                BufferedImage imagenActual = ImageIO.read(archivoSeleccionado);

                int width = imagenActual.getWidth();
                int height = imagenActual.getHeight();

                int[][] pixeles = new int[width][height];

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int color = imagenActual.getRGB(i, j);
                        pixeles[i][j] = color;
                    }
                }
                modelo.setPixeles(pixeles);
                parent.pack();
                logger.info("La imagen se obtuvo correctamente para ser abierta");
            } catch (IOException ex) {
                logger.error("No se pudo cargar la imagen");
                JOptionPane.showMessageDialog(parent, "Error al abrir la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
