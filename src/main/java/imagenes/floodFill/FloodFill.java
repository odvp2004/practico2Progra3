package imagenes.floodFill;

public class FloodFill {

    private int[][] image;

    int rango;


    public boolean valorizarColores(int x, int y, int colorReferencia) {
        int color = image[x][y];

        int redColor = (color >> 16) & 0xFF;
        int greenColor = (color >> 8) & 0xFF;
        int blueColor = color & 0xFF;

        int[] colorRGB = {redColor, greenColor, blueColor};

        int redColorReferencia = (colorReferencia >> 16) & 0xFF;
        int greenColorReferencia = (colorReferencia >> 8) & 0xFF;
        int blueColorReferencia = colorReferencia & 0xFF;

        int[] colorReferenciaRGB = {redColorReferencia,greenColorReferencia,blueColorReferencia};

        for(int i=0; i<3; i++){
            if(!((colorRGB[i] >= colorReferenciaRGB[i] && colorRGB[i] <= colorReferenciaRGB[i] + rango) || (colorRGB[i] <= colorReferenciaRGB[i] && colorRGB[i] >= colorReferenciaRGB[i] - rango))){
                return false;
            }
        }
        return true;
    }

    public int[][] floodFill(int[][] image, int x, int y, int newColor, int rango) {
        try {
            this.image = image;
            this.rango = rango;
            int oldColor = image[x][y];
            if (oldColor != newColor) {
                fill(x, y, oldColor, newColor);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return image;

    }

    // Método recursivo para rellenar
    private void fill(int x, int y, int oldColor, int newColor) {

        // Verifica los límites de la imagen
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return;
        }

        // Verifica si la celda actual es del color que queremos reemplazar
        if(!valorizarColores(x,y,oldColor)){
            return;
        }

        // Cambia el color actual al nuevo color

        image[x][y] = newColor;


        // Llama recursivamente para las 4 direcciones (arriba, abajo, izquierda, derecha)

        fill(x, y+1, oldColor, newColor);    //arriba
        fill(x+1, y, oldColor, newColor);    //derecha
        fill(x, y-1, oldColor, newColor);    //abajo
        fill(x-1, y, oldColor, newColor);    //izquierda

    }

}

