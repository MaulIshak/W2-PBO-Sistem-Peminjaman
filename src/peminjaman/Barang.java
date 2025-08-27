package peminjaman;

public class Barang {
  private String namaBarang;
  private int kuantitas;

  public Barang(String namaBarang, String kodeBarang) {
    this.namaBarang = namaBarang;
  }

  public String getNamaBarang() {
    return this.namaBarang;
  }

  public void setNamaBarang(String namaBarang) {
    this.namaBarang = namaBarang;
  }

  public int getKuantitas() {
    return this.kuantitas;
  }

  public void setKuantitas(int kuantitas) {
    this.kuantitas = kuantitas;
  }

  public void pinjam() {
    this.kuantitas--;
  }
}
