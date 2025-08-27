package peminjaman;

/**
 * Kelas Barang, tidak ada perubahan signifikan.
 * Tetap bertanggung jawab atas kuantitasnya sendiri.
 */
public class Barang {
  private static int counter = 0;
  private String kodeBarang;
  private String namaBarang;
  private int kuantitas;

  public Barang(String namaBarang, int kuantitasAwal) {
    this.kodeBarang = "BRG-" + String.format("%03d", ++counter);
    this.namaBarang = namaBarang;
    this.kuantitas = kuantitasAwal;
  }

  public boolean kurangiKuantitas(int jumlah) {
    if (jumlah > 0 && this.kuantitas >= jumlah) {
      this.kuantitas -= jumlah;
      return true;
    }
    return false;
  }

  public void tambahKuantitas(int jumlah) {
    if (jumlah > 0) {
      this.kuantitas += jumlah;
    }
  }

  // --- Getter ---
  public String getKodeBarang() {
    return this.kodeBarang;
  }

  public String getNamaBarang() {
    return this.namaBarang;
  }

  public int getKuantitas() {
    return this.kuantitas;
  }
}
