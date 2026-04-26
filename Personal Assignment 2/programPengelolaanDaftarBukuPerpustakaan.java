import java.util.Scanner;

// Class Node digunakan untuk menyimpan data satu buku
class Node {
    String kodeBuku, judul, penulis;
    Node next;

    // Konstruktor untuk membuat node baru berisi data buku
    Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null; // node baru belum terhubung ke node lain
    }
}

// Class LinkedListBuku digunakan untuk mengelola daftar buku
class LinkedListBuku {
    Node head; // menunjuk ke buku pertama dalam list
    int jumlahBuku; // menyimpan total jumlah buku

    // Konstruktor untuk inisialisasi linked list kosong
    LinkedListBuku() {
        head = null;
        jumlahBuku = 0;
    }

    // Menambahkan buku baru di bagian akhir linked list
    void tambahBuku(String kodeBuku, String judul, String penulis) {

        // Mengecek apakah kode buku melebihi batas karakter
        if (kodeBuku.length() > 5) {
            System.out.println("Gagal! Kode buku maksimal 5 karakter.");
            return;
        }

        // Membuat node baru untuk buku yang akan ditambahkan
        Node newNode = new Node(kodeBuku, judul, penulis);

        // Jika list kosong, node baru menjadi head
        if (head == null) {
            head = newNode;
        } else {
            // Mencari node terakhir dalam list
            Node current = head;
            while (current.next != null) current = current.next;

            // Menghubungkan node terakhir ke node baru
            current.next = newNode;
        }

        jumlahBuku++; // menambah total buku
        System.out.println("Data berhasil ditambahkan!");
    }

    // Menghapus buku pada posisi terakhir
    void hapusBuku() {

        // Mengecek apakah list kosong
        if (head == null) {
            System.out.println("Tidak ada data untuk dihapus.");
            return;
        }

        // Jika hanya ada satu node
        if (head.next == null) {
            System.out.println("Buku \"" + head.judul + "\" berhasil dihapus.");
            head = null;
        } else {
            // Mencari node sebelum node terakhir
            Node current = head;
            while (current.next.next != null) current = current.next;

            // Menghapus node terakhir
            System.out.println("Buku \"" + current.next.judul + "\" berhasil dihapus.");
            current.next = null;
        }

        jumlahBuku--; // mengurangi jumlah buku
    }

    // Mencari buku berdasarkan kode buku
    void cariBuku(String kodeBuku) {
        Node current = head;

        // Melakukan traversal untuk mencari data yang cocok
        while (current != null) {
            if (current.kodeBuku.equalsIgnoreCase(kodeBuku)) {

                // Menampilkan data jika ditemukan
                System.out.println("Buku ditemukan:");
                System.out.println("Kode   : " + current.kodeBuku);
                System.out.println("Judul  : " + current.judul);
                System.out.println("Penulis: " + current.penulis);
                return;
            }
            current = current.next;
        }

        // Jika tidak ditemukan sampai akhir list
        System.out.println("Buku tidak ditemukan.");
    }

    // Menampilkan seluruh data buku dalam linked list
    void tampilkanSemuaBuku() {

        // Mengecek apakah list kosong
        if (head == null) {
            System.out.println("Daftar buku kosong.");
            return;
        }

        System.out.println("\nDaftar Buku:");
        System.out.println("------------------------------------------------------------");

        Node current = head;
        int nomor = 1;

        // Menampilkan semua node satu per satu
        while (current != null) {
            System.out.println(nomor++ + ". Kode: " + current.kodeBuku +
                " | Judul: " + current.judul +
                " | Penulis: " + current.penulis);
            current = current.next;
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total Buku: " + jumlahBuku);
    }

    // Mengecek apakah jumlah buku sudah minimal 5
    boolean cukupData() {
        return jumlahBuku >= 5;
    }
}

// Class utama untuk menjalankan program
public class programPengelolaanDaftarBukuPerpustakaan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListBuku daftarBuku = new LinkedListBuku();

        // Loop utama program menu
        while (true) {
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            String inputMenu = scanner.nextLine().trim();
            int pilihan;

            // Validasi input agar hanya menerima angka
            try {
                pilihan = Integer.parseInt(inputMenu);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka 1-5.");
                continue;
            }

            // Menu tambah buku
            if (pilihan == 1) {
                System.out.print("Masukkan Kode Buku (maks 5 karakter): ");
                String kode = scanner.nextLine().trim();

                if (kode.length() > 5) {
                    System.out.println("Gagal! Kode buku maksimal 5 karakter.");
                    continue;
                }

                System.out.print("Masukkan Judul: ");
                String judul = scanner.nextLine().trim();
                System.out.print("Masukkan Penulis: ");
                String penulis = scanner.nextLine().trim();

                daftarBuku.tambahBuku(kode, judul, penulis);

            // Menu hapus buku
            } else if (pilihan == 2) {
                daftarBuku.hapusBuku();

            // Menu cari buku
            } else if (pilihan == 3) {
                System.out.print("Masukkan Kode Buku yang dicari: ");
                String cariKode = scanner.nextLine().trim();
                daftarBuku.cariBuku(cariKode);

            // Menu tampilkan semua buku
            } else if (pilihan == 4) {
                daftarBuku.tampilkanSemuaBuku();

            // Menu keluar program
            } else if (pilihan == 5) {
                if (!daftarBuku.cukupData()) {
                    System.out.println("Tidak bisa keluar! Jumlah buku saat ini: " +
                        daftarBuku.jumlahBuku + ". Minimal harus 5 buku.");
                } else {
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                }

            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih menu 1-5.");
            }
        }

        scanner.close(); // menutup scanner setelah program selesai
    }
}