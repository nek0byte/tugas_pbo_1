import java.io.IOException;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Mahasiswa> mahasiswa = new HashMap<>();
    private static final Map<String, Matkul> matkul = new HashMap<>();

    public static void main(String[] args) {
        data();
        while (true) {
            menu();
            String opsi = sc.nextLine().trim();
            switch (opsi) {
                case "1": addMhs(); break;
                case "2" : addMatkul(); break;
                case "3" : enroll(); break;
                case "4" : inputNilai(); break;
                case "5" : printKHS(); break;
                case "6" : printMahasiswa(); break;
                case "0" : System.out.println("SYBAU🥀"); return;
                default: System.out.println("Pilihan salah njir");break;
            }
        }
    }

    private static void menu() {
        System.out.println("\n========== KHS ==========");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tambah Matkul");
        System.out.println("3. Enroll Mahasiswa");
        System.out.println("4. Input Nilai");
        System.out.println("5. Cetak KHS (per semester)");
        System.out.println("6. List Mahasiswa");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }

    private static void addMhs() {
        System.out.print("NIM: "); String nim = sc.nextLine().trim();
        System.out.print("Nama: "); String nama = sc.nextLine().trim();
        mahasiswa.put(nim, new Mahasiswa(nim, nama));
        System.out.println("Mahasiswa berhasil tambahkan");
    }

    private static void addMatkul() {
        System.out.print("Kode MK: "); String kode = sc.nextLine().trim();
        System.out.print("Nama MK: "); String nama = sc.nextLine().trim();
        System.out.print("SKS: "); int sks = Integer.parseInt(sc.nextLine().trim());
        matkul.put(kode, new Matkul(kode, nama, sks));
        System.out.println("Matkul berhasil tambahkan");
    }

    private static void enroll() {
        System.out.print("Nim: "); String nim = sc.nextLine().trim();
        Mahasiswa s = mahasiswa.get(nim);
        if (s == null) {
            System.out.println("Mahasiswa tidak ditemukan");
            return;
        }
        System.out.print("kode MK: "); String kode = sc.nextLine().trim();
        Matkul m = matkul.get(kode);
        if (m == null) {
            System.out.println("Matkul tidak ditemukan");
            return;
        }
        System.out.print("Semester: "); int semester = Integer.parseInt(sc.nextLine().trim());
        s.enroll(m, semester);
        System.out.println("Enroll berhasil.");
    }

    public static void inputNilai() {
        System.out.print("NIM: "); String nim = sc.nextLine().trim();
        Mahasiswa s = mahasiswa.get(nim);
        if (s == null) {
            System.out.println("Mahasiswa tidak ditemukan");
        }
        System.out.print("Kode MK: "); String kode = sc.nextLine().trim();
        System.out.print("Semester: "); int semester = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nilai"); String n = sc.nextLine().trim();
        Nilai nilai = Nilai.fromString(n);
        if (nilai == null) {
            System.out.println("Format nilai salah");
            return;
        }
        boolean ok = s.setNilai(kode, semester, nilai);
        System.out.println(ok ? "Nilai disimpan" : "Enrollment tidak ditemukan");
    }

    private static void printKHS() {
        System.out.print("NIM: "); String nim = sc.nextLine().trim();
        Mahasiswa s = mahasiswa.get(nim);
        if (s == null) {
            System.out.println("Mahasiswa tidak ditemukan");
        }
        System.out.print("Semester: "); int semester = Integer.parseInt(sc.nextLine().trim());
        KHSPrinter.printKHS(s, semester);
        double ipk = KHSPrinter.hitungIPK(s);
        System.out.printf("IPK: %f\n", ipk);
    }

    private static void printMahasiswa() {
        System.out.println("Daftar Mahasiswa");
        for (Mahasiswa m : mahasiswa.values()) {
            System.out.println(m.getNim() + " - " + m.getNama());
        }
    }

    private static void data() {
        matkul.put("MK001", new Matkul("MK001", "AlgoDat", 3));
        matkul.put("MK002", new Matkul("MK002", "BasDat", 3));
        matkul.put("MK003", new Matkul("MK003", "PBO", 3));
        matkul.put("MK004", new Matkul("MK004", "OS", 2));
        matkul.put("MK005", new Matkul("MK005", "SisKen", 2));
        matkul.put("MK006", new Matkul("MK006", "Praktikum AlgoDat", 1));

        mahasiswa.put("F1B001", new Mahasiswa("F1B001", "Ambatukam"));

        mahasiswa.get("F1B001").enroll(matkul.get("MK001"),1);
        mahasiswa.get("F1B001").enroll(matkul.get("MK002"),1);
        mahasiswa.get("F1B001").enroll(matkul.get("MK003"),1);
        mahasiswa.get("F1B001").enroll(matkul.get("MK004"),3);
        mahasiswa.get("F1B001").enroll(matkul.get("MK005"),2);
        mahasiswa.get("F1B001").enroll(matkul.get("MK006"),4);

        mahasiswa.get("F1B001").setNilai("MK001", 1, Nilai.A);
        mahasiswa.get("F1B001").setNilai("MK002", 1, Nilai.B);
        mahasiswa.get("F1B001").setNilai("MK003", 1, Nilai.B);
        mahasiswa.get("F1B001").setNilai("MK004", 3, Nilai.A);
        mahasiswa.get("F1B001").setNilai("MK005", 2, Nilai.C);
        mahasiswa.get("F1B001").setNilai("MK006", 4, Nilai.C);
    }
}
