package imagenes.manejoDeImagen;

import imagenes.excepciones.ImagenException;
import imagenes.modelo.Imagen;

import javax.swing.*;

public interface IManejoDeImagen {

    /**
     * Cualquier tipo de manejo de imagen que se haga debe implementar esta clase.
     */

    void hacer(JFrame parent, Imagen modelo) throws ImagenException;
}
