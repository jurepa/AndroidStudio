package com.iesnervion.pjarana.neverstopclicking;

import java.io.Serializable;

/**
 * Created by pjarana on 19/02/18.
 * Descrip
 */

public class DatosJuego implements Serializable {

    private String clicks;
    private String haTerminado;

    public DatosJuego() {
        this.clicks=null;
        this.haTerminado=null;
    }

    public DatosJuego(String clicks, String haTerminado) {
        this.clicks = clicks;
        this.haTerminado = haTerminado;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getHaTerminado() {
        return haTerminado;
    }

    public void setHaTerminado(String haTerminado) {
        this.haTerminado = haTerminado;
    }
}
