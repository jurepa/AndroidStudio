package com.iesnervion.pjarana.neverstopclicking;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by pjarana on 19/02/18.
 */

public class ByteArrayConverter
{
    public DatosJuego deserializeBytes(byte[] bytes)
    {
        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        DatosJuego obj=null;
        try {
            ois = new ObjectInputStream(bytesIn);
            obj =(DatosJuego) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally
        {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }


    public  byte[] serializeObject(DatosJuego obj)
    {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] bytes=null;
        try {
            oos = new ObjectOutputStream(bytesOut);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try {
                oos.close();
                bytesOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bytes = bytesOut.toByteArray();
        return bytes;
    }
}
