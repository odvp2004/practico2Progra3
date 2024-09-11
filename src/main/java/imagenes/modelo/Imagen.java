package imagenes.modelo;

import imagenes.FloodFill.FloodFill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Arrays;

public class Imagen {
    private String rutaDeImagen;
    private String nombreImagen;
    private static final Logger logger = LogManager.getRootLogger();
    private final String OBSERVER_PIXELES = "OBSERVABLE PIXELES";
    private final String OBSERVER_IMAGEN = "OBSERVABLE_IMAGEN";
    private BufferedImage imagen;
    private int[][] pixeles;
    private int width;
    private int height;
    private PropertyChangeSupport supportObserver;
    private FloodFill painter;

    private int rango;

    public Imagen(String nombreImagen) {
        String directorio = "src/main/java/imagenes/ejemplos/";
        this.rutaDeImagen = directorio + nombreImagen;
        this.nombreImagen = nombreImagen;
        supportObserver = new PropertyChangeSupport(this);
        rango = 10;
        try {
            imagen = ImageIO.read(new File(rutaDeImagen));
            logger.info("Imagen inicializada");
            painter = new FloodFill();

            width = imagen.getWidth();
            height = imagen.getHeight();
            pixeles = new int[width][height];
            pixeles = setColores(pixeles);

        } catch (Exception e) {
            logger.error("La imagen no se ha podido cargar");
        }

    }

    public void setPixeles(int[][] nuevosPixeles) {
        int[][] oldPixeles = Arrays.copyOf(pixeles, pixeles.length);  // copia superficial
        this.pixeles = nuevosPixeles;
        supportObserver.firePropertyChange(OBSERVER_PIXELES, oldPixeles, pixeles);
        logger.info("Se han establecido un nuevo grupo de pixeles en la matriz");
    }

    public void pintar(Point point, int color) {
        setPixeles(painter.floodFill(pixeles, point.x, point.y, color, rango));
        logger.info("Se han pintado pixeles");
    }

    public void addObserver(PropertyChangeListener observer) {
        supportObserver.addPropertyChangeListener(observer);
        logger.debug("Se ha añadido un nuevo observador");
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(new Color(pixeles[i][j]));
                g.fillRect(i, j, 1, 1);
            }
        }
        logger.debug("Se han pintado los pixeles");
    }

    public int getRango() {
        return rango;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixeles() {
        return pixeles;
    }

    public int[][] setColores(int[][] pixeles) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = imagen.getRGB(i, j);
                pixeles[i][j] = color;
            }
        }
        logger.debug("Se han cargado los colores de la imagen en una matriz de pixeles");
        return pixeles;
    }

    public void setRango(int rango) {
        int oldRango = this.rango;
        this.rango = rango;
        supportObserver.firePropertyChange(OBSERVER_PIXELES, oldRango, this.rango);
        logger.info("Se han establecido un nuevo rango de alcance para los colores");
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setImagen(BufferedImage newImagen) {
        BufferedImage oldImage = imagen;
        imagen = newImagen;
        width = imagen.getWidth();
        height = imagen.getHeight();
        setPixeles(setColores(new int[width][height]));
        supportObserver.firePropertyChange(OBSERVER_IMAGEN, oldImage, imagen);
        logger.info("Se ha cargado una nueva imagen");
    }


}
