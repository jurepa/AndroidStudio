package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by pjarana on 22/11/17.
 */

public class ListadoProductos extends Application
{
    private ArrayList<Producto> listado;
    private ArrayList<ProductoAComprar>cesta;
    public ListadoProductos()
    {
        this.listado=new ArrayList<Producto>();
        //Productos de Moda
        //Colores dependiendo de la talla
        Color[]zapato40={new Color("Gris",R.drawable.zapato_gris),new Color("Negro",R.drawable.zapato_negro)};
        Color[]zapato41={new Color("Marron",R.drawable.zapato_marron)};
        Color[]camiseta40={new Color("Marron",R.drawable.camiseta_marron),new Color("Roja",R.drawable.camiseta_roja)};
        Color[]camiseta39={new Color("Negra",R.drawable.camiseta_negra)};
        Color[]pantalon38={new Color("Beige",R.drawable.pantalon_beige),new Color("Caqui",R.drawable.pantalon_caqui),new Color("Negro",R.drawable.pantalon_negro)};
        Color[]pantalon39={new Color("Beige",R.drawable.pantalon_beige),new Color("Caqui",R.drawable.pantalon_caqui)};

        Producto zapato1=new Producto("Zapato",new Talla(40,zapato40),20,"Moda","Gran Zapato");
        Producto zapato2=new Producto("Zapato",new Talla(41,zapato41),30,"Moda","Gran Zapato");
        Producto camiseta1=new Producto("Camiseta",new Talla(40,camiseta40),15,"Moda","Gran Camiseta");
        Producto camiseta2=new Producto("Camiseta",new Talla(39,camiseta39),13,"Moda","Gran Camiseta");
        Producto pantalon1=new Producto("Pantalon",new Talla(38,pantalon38),10,"Moda","Gran pantalon");
        Producto pantalon2=new Producto("Pantalon",new Talla(39,pantalon39),11,"Moda","Gran pantalon");
        //Fin productos Moda
        //Productos Muebles
        //String nombre, int precio, Color color, String categoria, String descripcion
        Color[]coloresMuebles={new Color("Beige",R.drawable.mesa_beige),new Color("Rojiza",R.drawable.mesa_rojiza)};
        Producto mesa1=new Producto("Mesa",30,coloresMuebles,"Mueble","Gran Mesa");

        this.listado.add(zapato1);
        this.listado.add(zapato2);
        this.listado.add(camiseta1);
        this.listado.add(camiseta2);
        this.listado.add(pantalon1);
        this.listado.add(pantalon2);
        this.listado.add(mesa1);
        this.cesta=new ArrayList<ProductoAComprar>();
    }

    public ArrayList<Producto> getListado() {
        return listado;
    }

    public void setListado(ArrayList<Producto> listado) {
        this.listado = listado;
    }

    public ArrayList<ProductoAComprar> getCesta() {
        return cesta;
    }

    public void setCesta(ArrayList<ProductoAComprar> cesta) {
        this.cesta = cesta;
    }

    //Método para buscar el primer producto a mostrar en la segunda activity
    public Producto buscarProducto(String nombreProducto)
    {
        boolean encontrado=false;
        Producto p=null;
        for(int i=0;i<this.listado.size()&&encontrado==false;i++)
        {
            if(nombreProducto.equals(this.listado.get(i).getNombre()))
            {
                encontrado=true;
                p=this.listado.get(i);
            }
        }
        return p;
    }
    //Este método nos devolverá todas las tallas que tiene un producto
    public ArrayList<Integer> arrayTallas(Producto p)
    {
        ArrayList<Integer>tallas=new ArrayList<Integer>();

        for(int i=0;i<this.listado.size();i++)
        {
            if(p.getNombre().equals(this.listado.get(i).getNombre()))
            {
                tallas.add(this.listado.get(i).getTalla().getTalla());
            }
        }
        return tallas;
    }
    //Con este método sacaremos los colores para la talla que tengamos elegida, obviamente solo para productos de moda
    public ArrayList<String>arrayColoresModa(int talla,Producto p)
    {
        ArrayList<String>colores=new ArrayList<String>();
        for(int i=0;i<p.getTalla().getColores().length;i++)
        {
            if(p.getTalla().getTalla()==talla)
            {
                colores.add(p.getTalla().getColores()[i].getColor());
            }
        }
        return colores;
    }


    public int imagenProductoModa (Producto p, String color)
    {
        int imagen=0;
        for(int i=0;i<p.getTalla().getColores().length;i++)
        {
            if(p.getTalla().getColores()[i].getColor().equals(color))
            {
                imagen=p.getTalla().getColores()[i].getFoto();
            }
        }
        return imagen;
    }

    public ArrayList<String>arrayColoresMueble(Producto p)
    {
        ArrayList<String> colores = new ArrayList<String>();
        for (int i = 0; i < p.getColor().length; i++)
        {
            colores.add(p.getColor()[i].getColor());
        }
        return colores;
    }
    public int imagenProductoMueble (Producto p, String color)
    {
        int imagen=0;
        for(int i=0;i<p.getColor().length;i++)
        {
            if(p.getColor()[i].getColor().equals(color))
            {
                imagen=p.getColor()[i].getFoto();
            }
        }
        return imagen;
    }

    public Producto buscarProductoPorTallaNombre(String nombre, int talla)
    {
        Producto p=null;
        boolean encontrado=false;
        for(int i=0;i<this.listado.size()&&encontrado==false;i++)
        {
            if(nombre.equals(this.listado.get(i).getNombre())&&talla==this.listado.get(i).getTalla().getTalla())
            {
                encontrado=true;
                p=this.listado.get(i);
            }
        }
        return p;
    }
}
