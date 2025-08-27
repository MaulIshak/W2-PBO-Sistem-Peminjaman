package peminjaman;

import java.time.LocalDate;

public class Mahasiswa {
  private String nim;
  private String nama;

  public Mahasiswa(String nim, String nama) {
    this.nim = nim;
    this.nama = nama;
  }

  public String getNim() {
    return nim;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public Peminjaman pinjamBarang(Barang barang, int kuantitas) {
    Peminjaman peminjaman = new Peminjaman(this, barang, LocalDate.now(), kuantitas);

    if (barang.getKuantitas() > 0) {
      barang.pinjam();
      System.out.println(this.nama + " berhasil meminjam " + barang.getNamaBarang() + ".");
    } else {
      System.out.println("Barang " + barang.getNamaBarang() + " tidak tersedia.");
    }

    return peminjaman;
  }

  public void kembalikanBarang(Barang barang) {
  }
}
