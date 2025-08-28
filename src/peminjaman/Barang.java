package peminjaman;

/**
 * Kelas Barang merepresentasikan satu jenis barang.
 */
public class Barang {
    private String namaBarang;
    private int stok;

    public Barang(String namaBarang, int stokAwal) {
        this.namaBarang = namaBarang;
        this.stok = stokAwal;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && this.stok >= jumlah) {
            this.stok -= jumlah;
            return true; // Berhasil mengurangi stok
        }
        return false; // Gagal mengurangi stok
    }

    public void kembalikanStok() {
        this.stok++;
    }

    // Getter
    public String getNamaBarang() {
        return namaBarang;
    }

    public int getStok() {
        return stok;
    }
}
