package imagenes.vista;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ImagenPanel extends JPanel implements MouseListener, PropertyChangeListener {
    private Imagen modelo;
    private RightPanel rightpanel;
    private static final Logger logger = LogManager.getRootLogger();

    public ImagenPanel(Imagen modelo, RightPanel rightPanel) {
        this.addMouseListener(this);
        this.modelo = modelo;
        this.modelo.addObserver(this);
        this.rightpanel = rightPanel;
    }

    @Override
    public Dimension getPreferredSize() {
        if(modelo.getWidth() > 0 && modelo.getHeight()>0){
            return new Dimension(modelo.getWidth(), modelo.getHeight());
        }
        return new Dimension(500,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        logger.debug("Pinta los componentes");
        modelo.dibujar(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (rightpanel.getHerramienta().isSelected()) {
            modelo.pintar(e.getPoint(), rightpanel.getColorActual().getRGB(),rightpanel.getRango());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Imagen) {
            logger.info("Han ocurrido cambios en el modelo");
            repaint();
        }
    }
}
