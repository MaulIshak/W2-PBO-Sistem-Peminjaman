import java.util.Scanner;
import peminjaman.Mahasiswa;
import peminjaman.Teknisi;

public class App {
    public static void main(String[] args) {

        Teknisi teknisi = new Teknisi("Pak Ujang");
        Scanner scanner = new Scanner(System.in);

        teknisi.siapkanBarang("Proyektor Epson", 5);
        teknisi.siapkanBarang("Terminal Listrik", 10);
        teknisi.siapkanBarang("Kabel HDMI", 8);

        System.out.println("===== SELAMAT DATANG DI SISTEM PEMINJAMAN JTK =====");
        System.out.print("Masukkan Nama Anda: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM Anda: ");
        String nim = scanner.nextLine();

        // Buat objek Mahasiswa berdasarkan input
        Mahasiswa mahasiswa = new Mahasiswa(nim, nama);
        System.out.println("\nHalo, " + mahasiswa.getNama() + "! Silakan pilih menu.");

        int pilihan;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Lihat Barang Tersedia");
            System.out.println("2. Pinjam Barang");
            System.out.println("3. Kembalikan Barang");
            System.out.println("4. Lihat Riwayat Peminjaman Saya");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1:
                    teknisi.tampilkanBarangTersedia();
                    break;
                case 2:
                    mahasiswa.lakukanPeminjaman(teknisi, scanner);
                    break;
                case 3:
                    mahasiswa.kembalikanBarang(scanner);
                    break;
                case 4:
                    mahasiswa.lihatRiwayatPeminjaman();
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
