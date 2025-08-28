
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import peminjaman.Barang;
import peminjaman.Mahasiswa;
import peminjaman.Peminjaman;
import peminjaman.Teknisi;

public class IoUtils {
  // =================================================================
  // MENU TEKNISI
  // =================================================================
  public static void runMenuTeknisi(Teknisi teknisi, Scanner scanner) {
    int pilihan;
    do {
      System.out.println("\n--- MENU TEKNISI ---");
      System.out.println("1. Tambah Barang Baru");
      System.out.println("2. Lihat Semua Riwayat Peminjaman");
      System.out.println("3. Lihat Stok Barang");
      System.out.println("4. Kembali ke Menu Utama");
      System.out.print("Pilihan: ");
      pilihan = getPilihanMenu(scanner);

      switch (pilihan) {
        case 1:
          tambahBarangBaru(scanner, teknisi);
          break;
        case 2:
          lihatSemuaPeminjaman(teknisi);
          break;
        case 3:
          lihatStokBarang(teknisi);
          break;
        case 4:
          System.out.println("Kembali ke menu utama...");
          break;
        default:
          System.out.println("Pilihan tidak valid.");
      }
    } while (pilihan != 5);
  }

  public static void tambahBarangBaru(Scanner scanner, Teknisi teknisi) {
    System.out.println("\n--- Tambah Barang Baru ---");
    System.out.print("Masukkan nama barang: ");
    String nama = scanner.nextLine();
    System.out.print("Masukkan jumlah stok awal: ");
    int jumlah = scanner.nextInt();
    scanner.nextLine();
    teknisi.tambahBarang(nama, jumlah);
    System.out.println("Barang baru berhasil ditambahkan.");
  }

  public static void lihatSemuaPeminjaman(Teknisi teknisi) {
    List<Peminjaman> semuaPeminjaman = teknisi.getSemuaPeminjaman();
    if (semuaPeminjaman.isEmpty()) {
      System.out.println("\nBelum ada riwayat peminjaman sama sekali.");
      return;
    }
    System.out.println("\n=============== SEMUA RIWAYAT PEMINJAMAN ===============");
    tampilkanHeaderPeminjaman(true);
    int no = 1;
    for (Peminjaman p : semuaPeminjaman) {
      tampilkanDetailPeminjaman(p, no++, true);
    }
    System.out.println(
        "======================================================================================================================");
  }

  // =================================================================
  // MENU MAHASISWA
  // =================================================================
  public static void runMenuMahasiswa(Teknisi teknisi, Scanner scanner) {
    System.out.println("\n--- Sesi Mahasiswa ---");
    System.out.print("Masukkan NIM: ");
    String nim = scanner.nextLine();
    System.out.print("Masukkan Nama: ");
    String nama = scanner.nextLine();
    Mahasiswa mhs = new Mahasiswa(nim, nama);
    System.out.println("Login berhasil sebagai " + mhs.getNama() + ".");

    int pilihan;
    do {
      System.out.println("\n--- MENU MAHASISWA (" + mhs.getNama() + ") ---");
      System.out.println("1. Pinjam Barang");
      System.out.println("2. Kembalikan Barang");
      System.out.println("3. Lihat Riwayat Peminjaman Saya");
      System.out.println("4. Kembali ke Menu Utama");
      System.out.print("Pilihan: ");
      pilihan = getPilihanMenu(scanner);

      switch (pilihan) {
        case 1:
          lakukanPeminjaman(mhs, scanner, teknisi);
          break;
        case 2:
          lakukanPengembalian(mhs, scanner, teknisi);
          break;
        case 3:
          lihatRiwayatPribadi(mhs, teknisi);
          break;
        case 4:
          System.out.println("Kembali ke menu utama...");
          break;
        default:
          System.out.println("Pilihan tidak valid.");
      }
    } while (pilihan != 4);
  }

  public static void lakukanPeminjaman(Mahasiswa mhs, Scanner scanner, Teknisi teknisi) {
    System.out.println("\n--- Formulir Peminjaman Barang ---");
    lihatStokBarang(teknisi);
    System.out.print("\nPilih nomor barang yang akan dipinjam: ");
    int idxBarang = scanner.nextInt() - 1;
    scanner.nextLine();
    System.out.print("Jumlah yang akan dipinjam: ");
    int qty = scanner.nextInt();
    scanner.nextLine();

    Peminjaman peminjamanBaru = mhs.pinjamBarang(teknisi, idxBarang, qty);
    if (peminjamanBaru != null) {
      System.out.println("Peminjaman berhasil dicatat!");
    } else {
      System.out.println("Peminjaman GAGAL. Stok tidak mencukupi atau pilihan tidak valid.");
    }
  }

  public static void lakukanPengembalian(Mahasiswa mhs, Scanner scanner, Teknisi teknisi) {
    System.out.println("\n--- Formulir Pengembalian Barang ---");
    List<Peminjaman> pinjamanAktif = teknisi.getPeminjamanAktifByMahasiswa(mhs);

    if (pinjamanAktif.isEmpty()) {
      System.out.println("Anda tidak memiliki barang yang sedang dipinjam.");
      return;
    }

    System.out.println("Pilih barang yang akan dikembalikan:");
    tampilkanHeaderPeminjaman(false);
    for (int i = 0; i < pinjamanAktif.size(); i++) {
      tampilkanDetailPeminjaman(pinjamanAktif.get(i), (i + 1), false);
    }
    System.out.println("-------------------------------------------------------------------------------------");
    System.out.print("Pilihan: ");
    int pilihan = scanner.nextInt() - 1;
    scanner.nextLine();

    if (pilihan >= 0 && pilihan < pinjamanAktif.size()) {
      Peminjaman peminjaman = pinjamanAktif.get(pilihan);
      mhs.kembalikanBarang(peminjaman);
      System.out.println("Barang '" + peminjaman.getBarang().getNamaBarang() + "' berhasil dikembalikan.");
    } else {
      System.out.println("Pilihan tidak valid.");
    }
  }

  public static void lihatRiwayatPribadi(Mahasiswa mhs, Teknisi teknisi) {
    List<Peminjaman> riwayat = teknisi.getPeminjamanByMahasiswa(mhs);
    if (riwayat.isEmpty()) {
      System.out.println("\nAnda belum memiliki riwayat peminjaman.");
      return;
    }
    System.out.println("\n=============== RIWAYAT PEMINJAMAN " + mhs.getNama().toUpperCase() + " ===============");
    tampilkanHeaderPeminjaman(false);
    int no = 1;
    for (Peminjaman p : riwayat) {
      tampilkanDetailPeminjaman(p, no++, false);
    }
    System.out.println(
        "==================================================================================================");
  }

  // =================================================================
  // METODE BANTU (HELPERS)
  // =================================================================

  public static void lihatStokBarang(Teknisi teknisi) {
    System.out.println("\n--- Daftar Stok Barang ---");
    List<Barang> inventaris = teknisi.getDaftarBarang();
    System.out.printf("%-5s %-25s %-10s%n", "No.", "Nama Barang", "Stok");
    System.out.println("-------------------------------------------");
    for (int i = 0; i < inventaris.size(); i++) {
      Barang brg = inventaris.get(i);
      System.out.printf("%-5d %-25s %-10d%n", (i + 1), brg.getNamaBarang(), brg.getKuantitas());
    }
    System.out.println("-------------------------------------------");
  }

  public static int getPilihanMenu(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Input tidak valid. Harap masukkan angka.");
      scanner.next();
      System.out.print("Pilihan: ");
    }
    int pilihan = scanner.nextInt();
    scanner.nextLine();
    return pilihan;
  }

  public static void tampilkanHeaderPeminjaman(boolean showMahasiswa) {
    if (showMahasiswa) {
      System.out.printf("%-5s %-15s %-20s %-25s %-10s %-20s %-20s %-10s%n", "No.", "NIM", "Nama Mahasiswa",
          "Nama Barang", "Jumlah", "Waktu Pinjam", "Waktu Kembali", "Status");
      System.out.println(
          "----------------------------------------------------------------------------------------------------------------------");
    } else {
      System.out.printf("%-5s %-25s %-10s %-20s %-20s %-10s%n", "No.", "Nama Barang", "Jumlah", "Waktu Pinjam",
          "Waktu Kembali", "Status");
      System.out.println(
          "--------------------------------------------------------------------------------------------------");
    }
  }

  public static void tampilkanDetailPeminjaman(Peminjaman p, int no, boolean showMahasiswa) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    String waktuKembaliStr = (p.getWaktuPengembalian() != null) ? p.getWaktuPengembalian().format(formatter) : "-";
    String status = p.isStatusAktif() ? "DIPINJAM" : "KEMBALI";

    if (showMahasiswa) {
      System.out.printf("%-5d %-15s %-20s %-25s %-10d %-20s %-20s %-10s%n",
          no,
          p.getPeminjam().getNim(),
          p.getPeminjam().getNama(),
          p.getBarang().getNamaBarang(),
          p.getKuantitas(),
          p.getWaktuPeminjaman().format(formatter),
          waktuKembaliStr,
          status);
    } else {
      System.out.printf("%-5d %-25s %-10d %-20s %-20s %-10s%n",
          no,
          p.getBarang().getNamaBarang(),
          p.getKuantitas(),
          p.getWaktuPeminjaman().format(formatter),
          waktuKembaliStr,
          status);
    }
  }
}
