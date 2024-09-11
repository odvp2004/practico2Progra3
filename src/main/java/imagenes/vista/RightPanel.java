package imagenes.vista;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RightPanel extends JPanel {

    private JButton actualColorButton;
    private JToggleButton herramienta;
    private Color colorActual;
    private static final Logger logger = LogManager.getRootLogger();

    private JLabel infoImagenLabel;

    private JLabel rangoLabel;
    private JSpinner rangoSpinner;

    public RightPanel(Imagen modelo) {
        super(new GridBagLayout());
        colorActual = Color.WHITE;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 25);

        infoImagenLabel = new JLabel("Imagen: " + modelo.getNombreImagen());

        add(infoImagenLabel, gbc);

        gbc.gridy++;

        actualColorButton = new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }
        };
        actualColorButton.setMinimumSize(new Dimension(50, 50));
        actualColorButton.setBackground(colorActual);
        actualColorButton.setBorderPainted(false);
        actualColorButton.addActionListener(e -> {

            Color nuevoColor = JColorChooser.showDialog(this, "Select Color", colorActual);
            if (nuevoColor != null) {
                setColorActual(nuevoColor);
                logger.info("Nuevo color seleccionado para pintar");
            }

        });

        add(actualColorButton, gbc);

        gbc.gridy++;
        rangoLabel = new JLabel("Rango: " + modelo.getRango());
        add(rangoLabel, gbc);
        gbc.gridy++;

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 10, 50, 10);


        rangoSpinner = new JSpinner(spinnerModel);

        rangoSpinner.addChangeListener(e -> {
            int nuevoRango = (int) rangoSpinner.getValue();
            modelo.setRango(nuevoRango);
            rangoLabel.setText("Rango: " + modelo.getRango());
        });

        add(rangoSpinner, gbc);
        gbc.gridy++;

        herramienta = new JToggleButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }
        };
        herramienta.setMinimumSize(new Dimension(50, 50));
        herramienta.setBorderPainted(false);
        herramienta.setSelected(false);
        ImageIcon iconoHerramienta = new ImageIcon("src/main/java/imagenes/Icons/bote-de-pintura.png");
        Image iconoEscala = iconoHerramienta.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        herramienta.setIcon(new ImageIcon(iconoEscala));

        add(herramienta, gbc);

    }

    public JToggleButton getHerramienta() {
        return herramienta;
    }

    public void setColorActual(Color color) {
        colorActual = color;
        actualColorButton.setBackground(colorActual);
    }

    public Color getColorActual() {
        return colorActual;
    }
}
