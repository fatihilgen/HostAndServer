import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Date;
import java.net.InetAddress;
import java.io.IOException;
import java.io.FileWriter;


public class server {


    public static void main(String[] arg) throws Exception {

        String hostAD;
        String kullanici;
        String zaman,fakt,sayTop;
        File dosya=new File("server.txt");
        FileOutputStream output=new FileOutputStream(dosya,true);//server calistigi gibi dosya nesnesi server.txt dosyasını olusturacak
        dosya.createNewFile();
        int islemSayaci=1;
        String islemGoster=". İSLEM  = ";
        String yenibaglati="Yeni bağlantı = ";




        ServerSocket server=new ServerSocket(1452);//soketimizin (server tarafi) port nuamrasını berliliyoruz
        System.out.printf("\nServer basarili bir sekilde basladi");
        Socket client=server.accept();//istemciden gelen istegi kabul ediyor
        DataInputStream r=new DataInputStream(client.getInputStream());//r nesnesi ile istemciden gelen verileri alıyor
        PrintStream w=new PrintStream(client.getOutputStream());//w nesnesi ile istemciye veri gönderiyor
        DataInputStream in=new DataInputStream(System.in);

        String NewIP=client.getInetAddress().getHostName();//servere baglanan istemcinin kimligini String degerine attım
        InetAddress ip;
        String hostname;
        ip = InetAddress.getLocalHost();//servere kim baglandi bunu text 'e yazmak icin aldim
        /*Alttaki islemler txt dosyasına yazdırma  kısmı
        * NewIp ile IP adresini yazdırdım
        *Date simdi istemdi ne zaman baglandi zamanı kaydettim
        *
        * */
        hostname = ip.getHostName();
        System.out.println(NewIP);
        output.write(yenibaglati.getBytes());

        String bosluk="\n------\n";
        output.write(bosluk.getBytes());

        output.write(NewIP.getBytes());

        output.write(bosluk.getBytes());

        Date simdikizman=new Date();
        zaman=simdikizman.toString();
        output.write(zaman.getBytes());

        output.close();//Dosya kapandi




        while(true)//islem server kapanana kadar devam edecek
        {

            int secim=Integer.parseInt(r.readLine());//istemciden gelecek olan secimi aldım buradan
            String data=r.readLine();//r(read) ile gelen degeri data String veri türünün icinde saklıyoruz
            switch(secim)
            {
                case 1:

                    String faktSuresi="faktoriyel cevap verme zamanı  =  ";
                    FileOutputStream cikti=new FileOutputStream(dosya,true);//cikti nesnesi ile verileri text dosyasına yazdırmak icin olusturdum

                    int num=Integer.parseInt(data);//istemciden gelen faktoriyel icin deger
                    int fact=1;
                    while(num>0)
                    {
                        fact=fact*num--;
                    }
                    Date simdi=new Date();//sistem saatini aldım
                    fakt=simdi.toString();//sistem saatini Stringe cevirdim
                    cikti.write(islemSayaci);//her islem icin sayac calisacaktir
                    cikti.write(islemGoster.getBytes());
                    cikti.write(faktSuresi.getBytes());
                    cikti.write(fakt.getBytes());
                    w.println(fact);
                    System.out.printf("\nFaktoriyel hesaplandi ve istemciye gönderildi\n");
                    break;
                case 2:
                    String SayiTopCevapSuresi="Girilen sayinin Toplami icin cevap verme zamani  =  ";
                    FileOutputStream cikti2=new FileOutputStream(dosya,true);

                    int num1=Integer.parseInt(data);
                    int sum=0;
                    while(num1!=0)
                    {
                        sum=sum+(num1 % 10);num1=num1/10;
                    }
                    cikti2.write(islemSayaci);
                    cikti2.write(islemGoster.getBytes());
                    cikti2.write(SayiTopCevapSuresi.getBytes());
                    Date SayiTopCevap=new Date();
                    sayTop=SayiTopCevap.toString();
                    cikti2.write(sayTop.getBytes());

                    sum = (sum < 10) ? sum : rakamToplami(sum);
                    w.println(sum);
                    System.out.printf("\nsayinin rakamlari toplandi ve istemciye gönderildi\n");
                    break;

                case 0:
                    server.close();
                    System.exit(0);
                    break;
            }
            islemSayaci++;
        }

    }




    public static int rakamToplami ( int num)//bu fonksiyonu girilen sayinin rakamlari toplami icin yaptim
        {
            int sum = 0;
            while (num > 0) {
                System.out.println(num);
                sum = sum + num % 10;
                num = num / 10;
            }
            sum = (sum < 10) ? sum : rakamToplami(sum);
            return sum;
        }

}