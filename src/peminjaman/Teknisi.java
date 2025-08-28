package peminjaman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kelas Teknisi menjadi pusat data dan layanan inventaris.
 * Mengelola semua barang dan semua catatan peminjaman.
 */
public class Teknisi {
    private String nama;
    private List<Barang> daftarBarang;
    private List<Peminjaman> semuaPeminjaman;

    public Teknisi(String nama) {
        this.nama = nama;
        this.daftarBarang = new ArrayList<>();
        this.semuaPeminjaman = new ArrayList<>();
    }

    // --- Metode untuk Teknisi ---
    public void tambahBarang(String nama, int kuantitas) {
        this.daftarBarang.add(new Barang(nama, kuantitas));
    }
    // --- Metode Layanan untuk Sistem ---
    public void catatPeminjaman(Peminjaman peminjaman) {
        this.semuaPeminjaman.add(peminjaman);
    }

    // --- Getter untuk mendapatkan informasi ---
    public String getNama() {
        return this.nama;
    }

    public List<Barang> getDaftarBarang() {
        return new ArrayList<>(this.daftarBarang);
    }

    public List<Peminjaman> getSemuaPeminjaman() {
        return new ArrayList<>(this.semuaPeminjaman);
    }

    // Metode untuk menyaring data peminjaman berdasarkan mahasiswa
    public List<Peminjaman> getPeminjamanByMahasiswa(Mahasiswa mhs) {
        return semuaPeminjaman.stream()
                .filter(p -> p.getPeminjam().getNim().equals(mhs.getNim()))
                .collect(Collectors.toList());
    }

    public List<Peminjaman> getPeminjamanAktifByMahasiswa(Mahasiswa mhs) {
        return semuaPeminjaman.stream()
                .filter(p -> p.getPeminjam().getNim().equals(mhs.getNim()) && p.isStatusAktif())
                .collect(Collectors.toList());
    }
}
