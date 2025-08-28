package peminjaman;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas Teknisi berperan sebagai pengelola inventaris barang.
 */
public class Teknisi {
    private String nama;
    private List<Barang> daftarBarang;

    public Teknisi(String nama) {
        this.nama = nama;
        this.daftarBarang = new ArrayList<>();
    }

    // Method untuk menyiapkan barang di awal
    public void siapkanBarang(String namaBarang, int stok) {
        this.daftarBarang.add(new Barang(namaBarang, stok));
    }

    // Method untuk menampilkan daftar barang yang dia kelola
    public void tampilkanBarangTersedia() {
        // clear terminal

        System.out.println("\n--- Daftar Barang Tersedia ---");
        // for (int i = 0; i < daftarBarang.size(); i++) {
        // Barang brg = daftarBarang.get(i);
        // System.out.println((i + 1) + ". " + brg.getNamaBarang() + " (Stok: " +
        // brg.getStok() + ")");
        // }

        // print tabel barang
        System.out.printf("================================================================================%n");
        System.out.printf("| %-3s | %-30s | %-10s |%n", "No", "Nama Barang", "Stok");
        System.out.printf("--------------------------------------------------------------------------------%n");
        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang brg = daftarBarang.get(i);
            System.out.printf("| %-3s | %-30s | %-10s |%n", (i + 1), brg.getNamaBarang(), brg.getStok());
        }
        System.out.printf("================================================================================%n");

    }

    // Method untuk memberikan objek barang kepada mahasiswa
    public Barang getBarang(int index) {
        if (index >= 0 && index < daftarBarang.size()) {
            return daftarBarang.get(index);
        }
        return null; // Mengembalikan null jika pilihan tidak valid
    }
}
