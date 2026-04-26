import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class SistemKasirToko {

    // model untuk menyimpan data satu pelanggan
    static class Pelanggan {
        String nomorAntrian;
        String namaPelanggan;
        double totalBelanja;

        // konstruktor untuk mengisi semua field pelanggan
        Pelanggan(String nomorAntrian, String namaPelanggan, double totalBelanja) {
            this.nomorAntrian = nomorAntrian;
            this.namaPelanggan = namaPelanggan;
            this.totalBelanja = totalBelanja;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s - Rp%.0f", nomorAntrian, namaPelanggan, totalBelanja);
        }
    }

    // antrian pelanggan pakai queue supaya urutan dilayani sesuai kedatangan (fifo)
    static Queue<Pelanggan> antrian = new LinkedList<>();

    // riwayat transaksi pakai stack supaya yang terbaru muncul paling atas (lifo)
    static Stack<Pelanggan> riwayat = new Stack<>();

    static Scanner scanner = new Scanner(System.in);

    // tambahkan pelanggan baru ke belakang antrian
    static void tambahAntrian() {
        System.out.print("Masukkan Nomor Antrian: ");
        String nomor = scanner.nextLine().trim();

        // cek apakah nomor antrian sudah dipakai sebelumnya
        for (Pelanggan p : antrian) {
            if (p.nomorAntrian.equalsIgnoreCase(nomor)) {
                System.out.println("Nomor antrian sudah ada!");
                return;
            }
        }

        System.out.print("Masukkan Nama Pelanggan: ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan Total Belanja: ");
        double belanja;
        try {
            belanja = Double.parseDouble(scanner.nextLine().trim());
            if (belanja < 0) {
                System.out.println("Total belanja tidak boleh negatif!");
                return;
            }
        } catch (NumberFormatException e) {
            // tangkap input yang bukan angka
            System.out.println("Input tidak valid! Masukkan angka.");
            return;
        }

        // masukkan pelanggan ke akhir antrian
        antrian.offer(new Pelanggan(nomor, nama, belanja));
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    // ambil pelanggan terdepan dari antrian lalu simpan ke riwayat
    static void layaniPelanggan() {
        if (antrian.isEmpty()) {
            System.out.println("Antrian kosong, tidak ada pelanggan!");
            return;
        }

        // keluarkan pelanggan paling depan dari queue
        Pelanggan dilayani = antrian.poll();
        System.out.printf("Melayani pelanggan %s (%s)%n",
                dilayani.nomorAntrian, dilayani.namaPelanggan);
        System.out.printf("Total Belanja: Rp%.0f%n", dilayani.totalBelanja);

        // push ke stack sebagai catatan transaksi yang sudah selesai
        riwayat.push(dilayani);
        System.out.println("Transaksi disimpan ke riwayat.");
    }

    // tampilkan semua pelanggan yang sedang mengantri dari depan ke belakang
    static void tampilkanAntrian() {
        if (antrian.isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }

        System.out.println("--- Antrian Saat Ini ---");
        int urutan = 1;
        for (Pelanggan p : antrian) {
            System.out.printf("%d. %s%n", urutan++, p);
        }
        System.out.printf("Total: %d pelanggan dalam antrian.%n", antrian.size());
    }

    // tampilkan riwayat transaksi dari yang paling baru sampai paling lama
    static void lihatRiwayat() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }

        System.out.println("--- Riwayat Transaksi (Terbaru ke Lama) ---");
        // iterasi dari indeks teratas stack ke bawah
        for (int i = riwayat.size() - 1; i >= 0; i--) {
            System.out.printf("%d. %s%n", riwayat.size() - i, riwayat.get(i));
        }
        System.out.printf("Total: %d transaksi tercatat.%n", riwayat.size());
    }

    // cetak garis pemisah supaya tampilan menu lebih rapi
    static void cetakGaris() {
        System.out.println("================================");
    }

    // tampilkan menu utama
    static void tampilkanMenu() {
        System.out.println();
        cetakGaris();
        System.out.println("    === SISTEM KASIR TOKO ===");
        cetakGaris();
        System.out.println("1. Tambah Antrian");
        System.out.println("2. Layani Pelanggan");
        System.out.println("3. Tampilkan Antrian");
        System.out.println("4. Lihat Riwayat Transaksi");
        System.out.println("5. Keluar");
        cetakGaris();
        System.out.print("Pilih menu: ");
    }

    // titik masuk program, jalankan loop menu sampai user memilih keluar
    public static void main(String[] args) {
        boolean jalan = true;

        while (jalan) {
            tampilkanMenu();
            String inputMenu = scanner.nextLine().trim();
            System.out.println();

            switch (inputMenu) {
                case "1":
                    tambahAntrian();
                    break;
                case "2":
                    layaniPelanggan();
                    break;
                case "3":
                    tampilkanAntrian();
                    break;
                case "4":
                    lihatRiwayat();
                    break;
                case "5":
                    // cek total pelanggan yang pernah masuk (antrian + riwayat)
                    int totalPelanggan = antrian.size() + riwayat.size();
                    if (totalPelanggan < 5) {
                        System.out.printf("Tidak bisa keluar! Jumlah pelanggan saat ini: %d. Minimal harus 5 pelanggan.%n", totalPelanggan);
                    } else {
                        System.out.println("Terima kasih! Program selesai.");
                        jalan = false;
                    }
                    break;
                default:
                    System.out.println("Menu tidak valid! Pilih 1-5.");
            }
        }

        scanner.close();
    }
}