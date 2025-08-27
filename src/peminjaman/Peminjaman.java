package peminjaman;

import java.time.LocalDate;

public class Peminjaman {
  private Mahasiswa peminjam;
  private Barang barang;
  private LocalDate waktuPeminjaman;
  private LocalDate waktuPengembalian;
  private int kuantitas;
  private boolean dipinjam;

  public Peminjaman(Mahasiswa peminjam, Barang barang, LocalDate waktuPeminjaman, int kuantitas) {
    this.peminjam = peminjam;
    this.barang = barang;
    this.waktuPeminjaman = waktuPeminjaman;
    this.kuantitas = kuantitas;
    this.waktuPengembalian = null;
    this.dipinjam = true;
  }

  public Mahasiswa getPeminjam() {
    return peminjam;
  }

  public void setPeminjam(Mahasiswa peminjam) {
    this.peminjam = peminjam;
  }

  public Barang getBarang() {
    return barang;
  }

  public void setBarang(Barang barang) {
    this.barang = barang;
  }

  public LocalDate getWaktuPeminjaman() {
    return waktuPeminjaman;
  }

  public void setWaktuPeminjaman(LocalDate waktuPeminjaman) {
    this.waktuPeminjaman = waktuPeminjaman;
  }

  public LocalDate getWaktuPengembalian() {
    return waktuPengembalian;
  }

  public void setWaktuPengembalian(LocalDate waktuPengembalian) {
    this.waktuPengembalian = waktuPengembalian;
  }

  public int getKuantitas() {
    return kuantitas;
  }

  public void setKuantitas(int kuantitas) {
    this.kuantitas = kuantitas;
  }

  public boolean isDipinjam() {
    return dipinjam;
  }

  public void setDipinjam(boolean dipinjam) {
    this.dipinjam = dipinjam;
  }

}
