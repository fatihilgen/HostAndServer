import  java.net.*;
import java.io.*;
import java.util.Date;
import java.io.DataInputStream;
public class istemci
{

    public static void main(String arg[])throws Exception
    {
        String data,simdikizaman,saytopsimdikizaman;
        File dosya=new File("istemci.txt");
        dosya.createNewFile();

        Socket istemci=new Socket("127.0.0.1",1452);//sokettimimzin(istemci tarafi) IP adresini ve port numarasıni belirledik
        DataInputStream r=new DataInputStream(istemci.getInputStream());//DataInputStream den r nesnesini turettik sokete girecek olan veriler r nesnesi ile olacak
                                                            //client.getInputStream ile  sokete veri girisini sagladik
        PrintStream w=new PrintStream(istemci.getOutputStream());//PrintStream den w nesnesini türettik  w nesnesi ile soketten veri çıkışını w ile yaptık
                                                           //client.getOutputStream ile soketten veri çıkışını sağladık
        DataInputStream in=new DataInputStream(System.in);//sokete gondereceğimiz veriyi in ile alıyoruz
        String fakt="faktoriyel istek gonderme suresi  =  ";
        String sayTop="girilen sayiların toplamı süresi  =  ";
        while(true)
        {
            System.out.println("\t\t\n[ SEÇİM ]\n[1].Farktöriyel\n[2].Bir sayinin rakamlari toplami\n[0].Çıkış\n\nBir seçim giriniz :");
            int secim=Integer.parseInt(in.readLine());// Switch case icin girilecek olan secim degerinin buradan cektik
            switch(secim)

            {
                case 1: System.out.println("\nBir sayi giriniz:");//eger girilen sayi 1 ise faktoriyel hesabi yapacak
                    int num=Integer.parseInt(in.readLine());//girilen degeri alıp in nesnesi ile sokete gonderiyoruz
                    w.println(secim);//secimi ekrana yazdiriyoruz
                    w.println(num);//girilen degeri ekrana yaziyoruz
                    FileOutputStream output=new FileOutputStream(dosya,true);

                    Date zaman=new Date();
                    output.write(fakt.getBytes());
                    simdikizaman=zaman.toString();
                    output.write(simdikizaman.getBytes());
                    String fact=r.readLine();//soketten gelen yeni servere gonderdigimiz degeri alıyoruz
                    System.out.print("\nSayinin faktoriyeli :"+fact);//serverden gelen degeri ekrana yazdiriyoruz
                    break;
                case 2: System.out.println("\nBir sayi giriniz:");
                    int num1=Integer.parseInt(in.readLine());//girilen degeri alıp in nesnesi ile sokete gonderiyoruz
                    w.println(secim);//secimi ekrana yazdiriyoruz
                    w.println(num1);//girilen degeri ekrana yaziyoruz
                    String sum=r.readLine();
                    FileOutputStream giris=new FileOutputStream(dosya,true);
                    Date SayitopIstek=new Date();
                    giris.write(sayTop.getBytes());
                    saytopsimdikizaman=SayitopIstek.toString();
                    giris.write(saytopsimdikizaman.getBytes());
                    System.out.print("\nSayinin rakamlari toplamı :"+sum);
                    System.out.print("\nok>"+sum);
                    break;

                case 0: secim=0;
                    w.println(secim);
                    istemci.close();
                    System.exit(0);
                    break;
                default :  System.out.println("\nHatalı seçim");
                    break;

            }

        }
    }

}