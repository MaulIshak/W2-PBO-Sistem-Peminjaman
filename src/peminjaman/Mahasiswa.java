package peminjaman;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Kelas Mahasiswa sebagai aktor yang melakukan peminjaman dan pengembalian.
 */
public class Mahasiswa {
    private String nim;
    private String nama;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    /**
     * Behavior Mahasiswa untuk meminjam barang.
     * @return Objek Peminjaman jika berhasil, null jika gagal.
     */
    public Peminjaman pinjamBarang(Teknisi teknisi, int pilihanBarang, int kuantitas) {
        List<Barang> inventaris = teknisi.getDaftarBarang();
        if (pilihanBarang < 0 || pilihanBarang >= inventaris.size()) {
            return null; // Pilihan tidak valid
        }

        Barang barangDipilih = inventaris.get(pilihanBarang);
        boolean stokCukup = barangDipilih.kurangiKuantitas(kuantitas);

        if (stokCukup) {
            Peminjaman peminjamanBaru = new Peminjaman(this, barangDipilih, LocalDateTime.now(), kuantitas);
            teknisi.catatPeminjaman(peminjamanBaru); // Mendaftarkan peminjaman ke sistem pusat
            return peminjamanBaru;
        }
        return null;
    }

    /**
     * Behavior Mahasiswa untuk mengembalikan barang.
     */
    public void kembalikanBarang(Peminjaman peminjaman) {
        peminjaman.getBarang().tambahKuantitas(peminjaman.getKuantitas());
        peminjaman.setStatusSelesai();
    }

    // --- Getter ---
    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
}
