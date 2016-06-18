
package agac;

import java.io.*;
import java.util.*;

public class Agac {

   
    public static void main(String[] args) throws IOException {
        int value ;
        Scanner oku = new Scanner(System.in);
       Tree yeni = new Tree();
       
       yeni.ekle(50, 1);
       yeni.ekle(25, 1);
       yeni.ekle(75, 1);
       yeni.ekle(12, 1);
       yeni.ekle(37, 1);
       yeni.ekle(43, 1);
       yeni.ekle(30, 1);
       yeni.ekle(33, 1);
     
       
       while(true)
       {
           System.out.print("İstediğiniz işlemin baş hafini giriniz yazır, ");
           System.out.print("ekle, bul, sil, or dolaş: ");
           int secim = getChar();
           
           switch (secim)
           {
               case 'y':
                   yeni.yazdiragac();
                   break;
               
               case 'e':
                       System.out.print("Eklemek istediğiniz sayıyı giriniz:");
                       value = getInt();
                       yeni.ekle(value , value+0.9);
                       break;
                       
               case 'b':
                        System.out.print("Bulmak istediğiniz sayıyı giriniz:");
                        value = getInt();
                        Dugum found = yeni.Bul(value);
                        if(found != null)
                        {
                            System.out.print("Bulundu : ");
                            found.yazdırDugum();
                            System.out.println(" ");
                            
                        }
               else
                            System.out.print('\n'+ value + " Sayısı Bulunamadı !!!\n");
                            
                   break;
                   
               case 's' :
                   System.out.print("Silmek istediğiniz sayıyı giriniz : ");
                   value = getInt();
                   
                   boolean didsil = yeni.sil(value);
                   if(didsil)
                       System.out.print("Silinen : "+value +"\n");
                   else
                       System.out.print("Silinmem istenen bulunamadı : " + value + '\n');
                   break;
                   
               case 'd' :
                   System.out.print(" Tip giriniz  1(Preorder), 2(Inorder), 3(Postorder) \n");
                   value = getInt();
                   yeni.gezinme(value);
                   break;
                   
               default:
                   System.out.print("Geçersiz giriş !!");
                   
                   
                   
           }
       }
    }
    
     public static String getString() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    
    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }
    
    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}
