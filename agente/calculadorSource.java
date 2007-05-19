// Decompiled by Jad v1.5.7d. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Calculador.java

package calculador;

import java.util.Vector;

// Referenced classes of package calculador:
//            Pair

public class Calculador
{

    public Calculador()
    {
        energiaAlimentos = 0;
        inicializar();
    }

    public void inicializar()
    {
        energiaPacman = (new Double(Math.random() * 50D + 10D)).intValue();
        energiaEnemigo = (new Double(Math.random() * 50D + 10D)).intValue();
        energiaAlimentos = (new Double(Math.random() * 20D + 5D)).intValue();
        performance = 0;
        contEnemigosMuertos = 0;
        posiciones = new Vector();
        x = (new Double(Math.random() * 4D + 1.0D)).intValue();
        y = (new Double(Math.random() * 4D + 1.0D)).intValue();
        posiciones.add(new Pair(x, y));
    }

    public Vector inicializarEnemigo()
    {
        int tamanioVector = (new Double(Math.random() * 4D + 2D)).intValue();
        Vector salida = new Vector(tamanioVector);
        for(int i = 0; i < tamanioVector; i++)
        {
            Pair pos;
            do
            {
                x = (new Double(Math.random() * 4D + 1.0D)).intValue();
                y = (new Double(Math.random() * 4D + 1.0D)).intValue();
                pos = new Pair(x, y);
            } while(repetido(pos));
            salida.add(pos);
            posiciones.add(pos);
        }

        return salida;
    }

    private boolean repetido(Pair posicion)
    {
        int i = 0;
        boolean esta;
        for(esta = false; !esta && i < posiciones.size(); i++)
            if(((Pair)posiciones.elementAt(i)).equal(posicion))
                esta = true;

        return esta;
    }

    public Vector inicializarComida()
    {
        int tamanioVector = (new Double(Math.random() * 4D + 2D)).intValue();
        Vector salida = new Vector(tamanioVector);
        for(int i = 0; i < tamanioVector; i++)
        {
            Pair pos;
            do
            {
                x = (new Double(Math.random() * 4D + 1.0D)).intValue();
                y = (new Double(Math.random() * 4D + 1.0D)).intValue();
                pos = new Pair(x, y);
            } while(repetido(pos));
            salida.add(pos);
            posiciones.add(pos);
        }

        return salida;
    }

    public Pair getPosicionInicial()
    {
        Pair salida = new Pair(x, y);
        return salida;
    }

    public int getPerformance()
    {
        return performance;
    }

    public int calcularEnergiaPacMan(String a)
    {
        if(a.toLowerCase() == "arriba" || a.toLowerCase() == "abajo" || a.toLowerCase() == "izquierda" || a.toLowerCase() == "derecha")
        {
            energiaPacman -= (int)((double)energiaPacman * 0.20000000000000001D);
            performance++;
        } else
        if(a.toLowerCase() == "pelear")
        {
            energiaPacman -= (new Double((double)energiaPacman * 0.20000000000000001D + (double)energiaEnemigo * 0.20000000000000001D)).intValue();
            contEnemigosMuertos++;
            performance += 5 * contEnemigosMuertos;
            if(energiaPacman < 0)
                energiaPacman = 0;
        } else
        if(a.toLowerCase() == "comer")
            energiaPacman += (int)((double)energiaAlimentos * 0.80000000000000004D);
        return energiaPacman;
    }

    private int energiaPacman;
    private int energiaEnemigo;
    private int energiaAlimentos;
    private int x;
    private int y;
    private int contEnemigosMuertos;
    private int performance;
    private Vector posiciones;
}
