package imagenes.vista;

import imagenes.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RightPanel extends JPanel implements PropertyChangeListener {

    private JButton actualColorButton;
    private JToggleButton herramienta;
    private Color colorActual;
    private Imagen modelo;
    private static final Logger logger = LogManager.getRootLogger();
    private JLabel infoImagenLabel;
    private JLabel rangoLabel;
    private JSpinner rangoSpinner;

    public RightPanel(Imagen modelo) {
        super(new GridBagLayout());
        colorActual = Color.WHITE;
        this.modelo = modelo;
        modelo.addObserver(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 25);

        infoImagenLabel = new JLabel("Dimensiones: " );

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

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 10, 50, 10);
        rangoSpinner = new JSpinner(spinnerModel);

        rangoLabel = new JLabel("Rango: " + rangoSpinner.getValue());
        add(rangoLabel, gbc);
        gbc.gridy++;


        rangoSpinner.addChangeListener(e -> {
            int nuevoRango = (int) rangoSpinner.getValue();
            rangoLabel.setText("Rango: " + nuevoRango);
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

    public int getRango(){
        return (int) rangoSpinner.getValue();
    }

    public void setColorActual(Color color) {
        colorActual = color;
        actualColorButton.setBackground(colorActual);
    }

    public Color getColorActual() {
        return colorActual;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        infoImagenLabel.setText("Dimension: " + modelo.getWidth() + " x " + modelo.getHeight());
    }
}
