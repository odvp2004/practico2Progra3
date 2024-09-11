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

public class GuardarImagen implements IManejoDeImagen{

    @Override
    public void hacer(JFrame parent, Imagen modelo) throws ImagenException{

        String DIRECTORIO_DESTINO = "D:\\nuevasImagenes\\";

        if(modelo.getHeight() <=0 | modelo.getWidth() <= 0){
            throw new ImagenException(ImagenException.IMAGEN_NULA_MESSAGE);
        }

        BufferedImage imagen = new BufferedImage(modelo.getWidth(), modelo.getHeight(), BufferedImage.TYPE_INT_RGB);
        try {
            for (int x = 0; x < modelo.getWidth(); x++) {
                for (int y = 0; y < modelo.getHeight(); y++) {
                    // Establecer el color del píxel
                    imagen.setRGB(x, y, modelo.getPixeles()[x][y]);
                }
            }
            String nombreImagen = JOptionPane.showInputDialog(parent, "Ingrese el nombre del archivo:", "Guardar Imagen", JOptionPane.PLAIN_MESSAGE);

            if(nombreImagen == null){return;}
            if(nombreImagen.isEmpty()){throw new ImagenException(ImagenException.NOMBRE_VACIO_IMAGEN_MESSAGE);}
            if(!nombreImagen.matches("[a-zA-Z0-9 ]+")){throw new ImagenException(ImagenException.NOMBRE_INVALIDO_IMAGEN_MESSAGE);}

            File archivoSalida = new File(DIRECTORIO_DESTINO + nombreImagen + ".png");
            ImageIO.write(imagen, "png", archivoSalida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
