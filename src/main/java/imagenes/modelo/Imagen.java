package imagenes.modelo;

import imagenes.floodFill.FloodFill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

public class Imagen {
    private static final Logger logger = LogManager.getRootLogger();
    private final String OBSERVER_PIXELES = "OBSERVABLE PIXELES";
    private int[][] pixeles;
    private int width;
    private int height;
    private PropertyChangeSupport supportObserver;
    private FloodFill painter;

    public Imagen() {
        pixeles = new int[][]{};
        supportObserver = new PropertyChangeSupport(this);
        painter = new FloodFill();
    }

    public void setPixeles(int[][] nuevosPixeles) {
        int[][] oldPixeles = Arrays.copyOf(pixeles, pixeles.length);
        this.pixeles = nuevosPixeles;

        this.width = pixeles.length;
        this.height = pixeles[0].length;

        logger.info("Se han establecido un nuevo grupo de pixeles en la matriz");
        supportObserver.firePropertyChange(OBSERVER_PIXELES, oldPixeles, pixeles);

    }

    public void pintar(Point point, int color, int rango) {
        logger.info("Se han pintado pixeles con la cubeta");
        setPixeles(painter.floodFill(pixeles, point.x, point.y, color, rango));
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
        logger.debug("Se han pintado los pixeles en el panel principal");
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

    public boolean contains(Point point){
        return (point.x > 0 && point.x < width && point.y > 0 && point.y <height);
    }


}
