
import java.time.format.DateTimeFormatter;
import java.util.*;
import peminjaman.Barang;
import peminjaman.Mahasiswa;
import peminjaman.Peminjaman;

public class App {
    private static List<Barang> daftarBarang = new ArrayList<>();
    private static List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private static List<Peminjaman> daftarPeminjaman = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== MENU PEMINJAMAN BARANG =====");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tambah Mahasiswa");
            System.out.println("3. Peminjaman Barang");
            System.out.println("4. Lihat Daftar Peminjaman");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // konsumsi newline

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    tambahMahasiswa();
                    break;
                case 3:
                    peminjamanBarang();
                    break;
                case 4:
                    lihatDaftarPeminjaman();
                    break;
                case 5:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void tambahBarang() {
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jumlah barang: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        Barang barang = new Barang(nama, "");
        barang.setKuantitas(jumlah);
        daftarBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    private static void tambahMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama);
        daftarMahasiswa.add(mhs);
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private static void peminjamanBarang() {
        if (daftarMahasiswa.isEmpty() || daftarBarang.isEmpty()) {
            System.out.println("Data mahasiswa atau barang kosong!");
            return;
        }

        System.out.println("Pilih Mahasiswa:");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + daftarMahasiswa.get(i).getNama() + " (" + daftarMahasiswa.get(i).getNim() + ")");
        }
        System.out.print("Pilihan: ");
        int idxMhs = scanner.nextInt() - 1;
        scanner.nextLine();
        Mahasiswa mhs = daftarMahasiswa.get(idxMhs);

        System.out.println("Pilih Barang:");
        for (int i = 0; i < daftarBarang.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBarang.get(i).getNamaBarang() + " (stok: "
                    + daftarBarang.get(i).getKuantitas() + ")");
        }
        System.out.print("Pilihan: ");
        int idxBarang = scanner.nextInt() - 1;
        scanner.nextLine();
        Barang barang = daftarBarang.get(idxBarang);

        System.out.print("Jumlah yang dipinjam: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        if (barang.getKuantitas() >= qty) {
            barang.setKuantitas(barang.getKuantitas() - qty);
            Peminjaman p = new Peminjaman(mhs, barang, java.time.LocalDate.now(), qty);
            daftarPeminjaman.add(p);
            System.out.println("Peminjaman berhasil.");
        } else {
            System.out.println("Stok barang tidak mencukupi!");
        }
    }

    private static void lihatDaftarPeminjaman() {
        if (daftarPeminjaman.isEmpty()) {
            System.out.println("Belum ada data peminjaman.");
            return;
        }

        System.out.println("\n=== DAFTAR PEMINJAMAN ===");
        System.out.printf("%-15s %-20s %-15s %-10s %-15s%n",
                "NIM", "Nama Mahasiswa", "Nama Barang", "Jumlah", "Tanggal Pinjam");
        System.out.println("----------------------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Peminjaman p : daftarPeminjaman) {
            System.out.printf("%-15s %-20s %-15s %-10d %-15s%n",
                    p.getPeminjam().getNim(),
                    p.getPeminjam().getNama(),
                    p.getBarang().getNamaBarang(),
                    p.getKuantitas(),
                    p.getWaktuPeminjaman().format(formatter));
        }
    }
}
