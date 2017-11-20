package com.iesnervion.dleal.examenprimeraevaluacion.model;

/**
 * Created by dleal on 7/12/16.
 */

public class Imagen {

    private int img;
    private boolean escogido;

    public Imagen(int img, boolean escogido) {
        this.img = img;
        this.escogido = escogido;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isEscogido() {
        return escogido;
    }

    public void setEscogido(boolean escogido) {
        this.escogido = escogido;
    }
}
