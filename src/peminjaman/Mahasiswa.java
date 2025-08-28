package peminjaman;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Kelas Mahasiswa adalah Aktor Utama.
 * Dia memiliki data diri (state) dan bisa melakukan aksi (behavior).
 */
public class Mahasiswa {
    // State (Atribut) dari Mahasiswa
    private String nim;
    private String nama;
    private List<Peminjaman> riwayatPeminjaman;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.riwayatPeminjaman = new ArrayList<>(); // Setiap mahasiswa punya riwayatnya sendiri
    }

    // Behavior (Method) dari Mahasiswa
    public void lakukanPeminjaman(Teknisi teknisi, Scanner scanner) {
        System.out.println("\n--- Formulir Peminjaman ---");
        teknisi.tampilkanBarangTersedia();

        System.out.print("Pilih nomor barang yang akan dipinjam: ");
        int pilihanBarang = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Jumlah yang akan dipinjam: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        // Mahasiswa meminta barang dari teknisi
        Barang barangDipinjam = teknisi.getBarang(pilihanBarang);

        // Cek apakah barangnya ada dan stoknya cukup
        if (barangDipinjam != null && barangDipinjam.kurangiStok(jumlah)) {
            // Jika berhasil, buat catatan peminjaman dan simpan di riwayat
            Peminjaman peminjamanBaru = new Peminjaman(this, barangDipinjam, LocalDateTime.now(), jumlah);
            this.riwayatPeminjaman.add(peminjamanBaru);
            System.out.println("Peminjaman berhasil dicatat!");
        } else {
            System.out.println("Peminjaman GAGAL. Stok tidak cukup atau pilihan tidak valid.");
        }
    }

    public void lihatRiwayatPeminjaman() {
        if (riwayatPeminjaman.isEmpty()) {
            System.out.println("\nAnda belum pernah meminjam barang.");
            return;
        }

        System.out.println("\n--- RIWAYAT PEMINJAMAN " + this.nama.toUpperCase() + " ---");
        // Format tanggal
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Print tabel peminjaman
        System.out
                .printf("================================================================================================%n");

        System.out.printf("| %-3s | %-30s | %-10s | %-20s | %-20s |%n", "No.", "Nama Barang", "Jumlah",
                "Waktu Peminjaman",
                "Waktu Pengembalian");
        System.out
                .printf("-----------------------------------------------------------------------------------------------%n");
        int no = 1;
        for (Peminjaman p : riwayatPeminjaman) {
            Barang barang = p.getBarang();
            System.out.printf("| %-3s | %-30s | %-10s | %-20s | %-20s |%n", no++, barang.getNamaBarang(), p.getJumlah(),
                    p.getWaktuPinjam().format(formatter),
                    (p.getWaktuKembali() != null) ? p.getWaktuKembali().format(formatter) : null);
        }
        System.out
                .printf("===============================================================================================%n");
    }

    public void kembalikanBarang(Scanner scanner) {
        // cek apakah pernah meminjam
        if (riwayatPeminjaman.isEmpty()) {
            System.out.println("\nAnda belum pernah meminjam barang.");
            return;
        }
        // tampilkan riwayat peminjaman
        this.lihatRiwayatPeminjaman();
        
        System.out.print("Pilih nomor barang yang akan dikembalikan: ");
        int pilihan = scanner.nextInt() - 1;
        if (pilihan >= 0 && pilihan < riwayatPeminjaman.size()
                && riwayatPeminjaman.get(pilihan).getWaktuKembali() == null) {
            Peminjaman peminjamanDipilih = riwayatPeminjaman.get(pilihan);
            peminjamanDipilih.getBarang().kembalikanStok();
            peminjamanDipilih.setWaktuKembali(LocalDateTime.now());
            System.out.println("Barang berhasil dikembalikan.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }

    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
}
