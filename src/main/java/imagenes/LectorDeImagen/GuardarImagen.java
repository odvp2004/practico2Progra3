package imagenes.LectorDeImagen;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuardarImagen {
    private static final Logger logger = LogManager.getRootLogger();

    public GuardarImagen(JFrame parent, Imagen modelo) {

        String DIRECTORIO_DESTINO = "D:\\nuevasImagenes\\";

        BufferedImage imagen = new BufferedImage(modelo.getWidth(), modelo.getHeight(), BufferedImage.TYPE_INT_RGB);
        try {
            for (int x = 0; x < modelo.getWidth(); x++) {
                for (int y = 0; y < modelo.getHeight(); y++) {
                    // Establecer el color del píxel
                    imagen.setRGB(x, y, modelo.getPixeles()[x][y]);
                }
            }
            String nombreImagen = JOptionPane.showInputDialog(parent, "Ingrese el nombre del archivo:", "Guardar Imagen", JOptionPane.PLAIN_MESSAGE);
            File archivoSalida = new File(DIRECTORIO_DESTINO + nombreImagen + ".png");
            ImageIO.write(imagen, "png", archivoSalida);
            logger.info("Imagen guardada en: " + DIRECTORIO_DESTINO);

        } catch (IOException e) {
            logger.error("No se pudo guardar la imagen");
        }
    }
}
