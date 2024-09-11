package imagenes.excepciones;

public class ImagenException extends Exception {

    public static final String FORMATO_IMAGEN_MESSAGE = "La imagen debe ser de tipo .png o .jpg";
    public static final String EXISTENCIA_IMAGEN_MESSAGE = "La direccion de la imagen debe ser valida y existente";
    public static final String NOMBRE_VACIO_IMAGEN_MESSAGE = "El nombre no debe estar vacío";
    public static final String NOMBRE_INVALIDO_IMAGEN_MESSAGE = "El nombre debe contener solo caracteres alfa-numericos";
    public static final String IMAGEN_NULA_MESSAGE = "La imagen debe tener un ancho y alto mayor a 0";

    public ImagenException(String mensaje){
        super(mensaje);
    }
}
