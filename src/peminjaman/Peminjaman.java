package peminjaman;

import java.time.LocalDateTime;

/**
 * Kelas Peminjaman adalah kelas data sederhana (POJO)
 * untuk menyimpan informasi satu transaksi peminjaman.
 */
public class Peminjaman {
    private Mahasiswa peminjam;
    private Barang barang;
    private LocalDateTime waktuPinjam;
    private LocalDateTime waktuKembali;
    
    private int jumlah;

    public Peminjaman(Mahasiswa peminjam, Barang barang, LocalDateTime waktuPinjam, int jumlah) {
        this.peminjam = peminjam;
        this.barang = barang;
        this.waktuPinjam = waktuPinjam;
        this.jumlah = jumlah;
    }

    // Hanya Getter, karena data transaksi tidak seharusnya diubah setelah dibuat.
    public Mahasiswa getPeminjam() {
        return peminjam;
    }

    public Barang getBarang() {
        return barang;
    }

    public LocalDateTime getWaktuPinjam() {
        return waktuPinjam;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setPeminjam(Mahasiswa peminjam) {
        this.peminjam = peminjam;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public void setWaktuPinjam(LocalDateTime waktuPinjam) {
        this.waktuPinjam = waktuPinjam;
    }

    public LocalDateTime getWaktuKembali() {
        return waktuKembali;
    }

    public void setWaktuKembali(LocalDateTime waktuKembali) {
        this.waktuKembali = waktuKembali;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
