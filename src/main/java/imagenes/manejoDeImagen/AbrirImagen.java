package imagenes.manejoDeImagen;

import imagenes.excepciones.ImagenException;
import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AbrirImagen implements IManejoDeImagen{
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public void hacer(JFrame parent, Imagen modelo) throws ImagenException {
        JFileChooser fileChooser = new JFileChooser();
        String DIRECTORIO_RECURSOS = "D:\\NUR\\3er Semestre\\Programacion-III\\pr2v2\\src\\main\\java\\imagenes\\ejemplos";
        fileChooser.setCurrentDirectory(new File(DIRECTORIO_RECURSOS));

        fileChooser.setDialogTitle("Abrir Imagen");
        int resultado = fileChooser.showOpenDialog(parent);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            try {
                BufferedImage imagenActual = ImageIO.read(archivoSeleccionado);

                if(imagenActual == null){
                    throw new ImagenException(ImagenException.FORMATO_IMAGEN_MESSAGE);
                }

                logger.info("La imagen se obtuvo correctamente para ser abierta");


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

            } catch (IOException ex) {
                throw new ImagenException(ImagenException.EXISTENCIA_IMAGEN_MESSAGE);
            }
        }
    }

}
