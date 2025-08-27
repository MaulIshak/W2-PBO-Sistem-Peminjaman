package peminjaman;

import java.time.LocalDateTime;

/**
 * Kelas Peminjaman sebagai pencatat data transaksi.
 * Menggunakan LocalDateTime untuk presisi waktu.
 */
public class Peminjaman {
    private Mahasiswa peminjam;
    private Barang barang;
    private LocalDateTime waktuPeminjaman;
    private LocalDateTime waktuPengembalian;
    private int kuantitas;
    private boolean statusAktif;

    public Peminjaman(Mahasiswa peminjam, Barang barang, LocalDateTime waktuPeminjaman, int kuantitas) {
        this.peminjam = peminjam;
        this.barang = barang;
        this.waktuPeminjaman = waktuPeminjaman;
        this.kuantitas = kuantitas;
        this.waktuPengembalian = null;
        this.statusAktif = true;
    }

    public void setStatusSelesai() {
        this.statusAktif = false;
        this.waktuPengembalian = LocalDateTime.now();
    }

    // --- Getter ---
    public Mahasiswa getPeminjam() {
        return peminjam;
    }

    public Barang getBarang() {
        return barang;
    }

    public LocalDateTime getWaktuPeminjaman() {
        return waktuPeminjaman;
    }
    
    public LocalDateTime getWaktuPengembalian() {
        return waktuPengembalian;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public boolean isStatusAktif() {
        return statusAktif;
    }
}
