package peminjaman;

/**
 * Kelas Barang merepresentasikan satu jenis barang.
 * Dia bertanggung jawab atas stoknya sendiri (Enkapsulasi).
 */
public class Barang {
    private String namaBarang;
    private int stok;

    public Barang(String namaBarang, int stokAwal) {
        this.namaBarang = namaBarang;
        this.stok = stokAwal;
    }

    // Method ini adalah bagian dari enkapsulasi.
    // Kelas lain tidak bisa langsung mengubah stok, tapi harus "meminta".
    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && this.stok >= jumlah) {
            this.stok -= jumlah;
            return true; // Berhasil mengurangi stok
        }
        return false; // Gagal mengurangi stok
    }

    public void kembalikanStok() {
        this.stok += 1;
    }

    // Getter
    public String getNamaBarang() {
        return namaBarang;
    }

    public int getStok() {
        return stok;
    }
}
