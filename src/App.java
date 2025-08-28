import java.util.Scanner;
import peminjaman.Teknisi;

public class App {
    // Objek Teknisi menjadi pusat data untuk inventaris dan catatan peminjaman
    private static Teknisi teknisi = new Teknisi("Pak Ujang");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiDataAwal(teknisi);

        int pilihan;
        do {
            System.out.println("\n===== SISTEM INVENTARIS & PEMINJAMAN JTK =====");
            System.out.println("Pilih peran Anda:");
            System.out.println("1. Teknisi / Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");

            pilihan = IoUtils.getPilihanMenu(scanner);

            switch (pilihan) {
                case 1:
                    IoUtils.runMenuTeknisi(teknisi, scanner);
                    break;
                case 2:
                    IoUtils.runMenuMahasiswa(teknisi, scanner);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    private static void inisialisasiDataAwal(Teknisi teknisi) {
        teknisi.tambahBarang("Proyektor Epson", 5);
        teknisi.tambahBarang("Terminal Listrik 8-lubang", 10);
        teknisi.tambahBarang("Kabel HDMI 5m", 8);
    }

}
