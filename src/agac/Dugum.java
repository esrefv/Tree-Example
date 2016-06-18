

package agac;

import java.io.*;
import java.util.*;

public class Dugum {
    public int veri ;
    public double dveri;
    public Dugum solcocuk;
    public Dugum sagcocuk;
    
    public void yazdırDugum()
    {
        System.out.print("{ ");
        System.out.print(veri);
        System.out.print(",  ");
        System.out.print(dveri);
        System.out.print("} ");
    }
    
}

class Tree
{
    private Dugum kok;
    
    public Tree()
    { kok =null;}
    
    public Dugum Bul(int anahtar)
    {
        Dugum simdiki = kok ;
        
        while ( simdiki.veri != anahtar)
        {
            if(anahtar < simdiki.veri)
                simdiki = simdiki.solcocuk;
            else
                simdiki = simdiki.sagcocuk;
            
            if( simdiki == null)
                return null;
        }
        
        return simdiki ;
        }
    
    public void ekle(int id , double dd)
    {
        Dugum yenidugum = new Dugum();
        yenidugum.veri =id;
        yenidugum.dveri=dd;
        
        if(kok == null)
            kok = yenidugum ;
        else
        {
            Dugum simdiki = kok ;
            Dugum aile ;
            while (true)
            {
                aile = simdiki ;
                if (id < simdiki.veri)
                {
                    simdiki = simdiki.solcocuk;
                    
                    if(simdiki == null)
                    {
                        aile.solcocuk = yenidugum ;
                        return ;
                    }
                    
                }
                
                else
                {
                    simdiki = simdiki.sagcocuk;
                    if(simdiki == null)
                    {
                        aile.sagcocuk = yenidugum ;
                        return;
                    }
                }
            }
        }
    }
    
    public boolean sil(int anahtar)
    {
        Dugum simdiki = kok ;
        Dugum aile = kok ;
        boolean issolcocuk = true ;
        
        while ( simdiki.veri != anahtar)
        {
            aile = simdiki ;
            
            if(anahtar <simdiki.veri)
            {
                issolcocuk = true ;
                simdiki = simdiki.solcocuk;
                
            }
            
            else
            {
                issolcocuk = false ;
                simdiki = simdiki.sagcocuk;
            }
            
            if(simdiki == null)
                return false ;
        }
        
        if(simdiki.solcocuk == null && simdiki.sagcocuk == null)
        {
            if(simdiki == kok)
                kok = null;
            else if(issolcocuk)
                aile.solcocuk = null;
            else
                aile.sagcocuk = null;
        }
        
        else if(simdiki.sagcocuk == null)
            if(simdiki == kok)
                kok = simdiki.solcocuk;
            else if(issolcocuk)
                aile.solcocuk = simdiki.solcocuk;
        else
                aile.sagcocuk = simdiki.solcocuk;
        
        
        else if(simdiki.solcocuk == null)
            if(simdiki == kok)
                kok =simdiki.sagcocuk;
            else if(issolcocuk)
                aile.solcocuk = simdiki.sagcocuk;
        else
                aile.sagcocuk = simdiki.sagcocuk;
        
        else
        {
            Dugum varis = getvaris(simdiki);
            
            if(simdiki == kok)
                kok = varis;
            else if(issolcocuk)
                aile.solcocuk = varis;
            else
                aile.sagcocuk = varis ;
            
            varis.solcocuk = simdiki.solcocuk;
        }
        
        return true ;
    }
    
    
    private Dugum getvaris(Dugum sildugum)
    {
        Dugum varisailesi = sildugum ;
        Dugum varis = sildugum;
        Dugum simdiki =sildugum.sagcocuk;
        
        while(simdiki != null)
        {
            varisailesi = varis ;
            varis = simdiki ;
            simdiki = simdiki.solcocuk;
        }
        
        if(varis != sildugum.sagcocuk)
        {
            varisailesi.solcocuk = varis.sagcocuk;
            varis.sagcocuk = sildugum.sagcocuk;
        }
        
        return varis;
    }
    
    public void gezinme(int gezinmeTipi)
    {
        switch(gezinmeTipi)
                {
            case 1 : System.out.print("\n Preorder Gezinme");
                preorder(kok);
                break;
                
            case 2 : System.out.print("\n Inorder Gezinme ");
                inorder(kok);
                break;
                
            case 3 : System.out.print("\n Postorder Gezinme ");
                postorder(kok);
                break;
                
                }
        System.out.println(" ");
    }
    
    
    private void preorder(Dugum localkok)
    {
        if(localkok != null)
        {
            System.out.print(localkok.veri + "  ");
            preorder(localkok.solcocuk);
            preorder(localkok.sagcocuk);
        }
    }
    
    private void inorder(Dugum localkok)
    {
        if(localkok != null)
        {
            inorder(localkok.solcocuk);
            System.out.print(localkok.veri + "  ");
            inorder(localkok.sagcocuk);
        }
    }
    
    private void postorder(Dugum localkok)
    {
        if(localkok != null)
        {
            postorder(localkok.solcocuk);
            postorder(localkok.sagcocuk);
            System.out.print(localkok.veri + "  ");
        }
    }
    
    public void yazdiragac()
    {
        Stack globalstack = new Stack();
        globalstack.push(kok);
        int nbosluk = 32;
        boolean sıradakibosmu = false ;
        System.out.println(
        ".........................................................");
        
        while (sıradakibosmu == false )
        {
            Stack localstack = new Stack();
            sıradakibosmu = true;
                    
            for(int j = 0 ; j<nbosluk;j++)
                System.out.print(' ');
            
            while (globalstack.isEmpty()==false)
            {
                Dugum temp = (Dugum)globalstack.pop();
                
                if(temp != null)
                {
                    System.out.print(temp.veri);
                    localstack.push(temp.solcocuk);
                    localstack.push(temp.sagcocuk);
                    
                    
                    if(temp.solcocuk !=null || temp.sagcocuk != null)
                        sıradakibosmu = false ;
                    
                }
                else
                {
                    System.out.print("--");
                    localstack.push(null);
                    localstack.push(null);
                }
                
                for(int j=0 ;j < (nbosluk*2)-2 ; j++)
                    System.out.print(' ');
                
                
            }
            
            System.out.println();
            nbosluk/=2;
            
            while (localstack.isEmpty()==false)
                globalstack.push(localstack.pop());
            
            
            
        }
        
        System.out.println(
        ".......................................................");
    }
    
   
    
    
    }



