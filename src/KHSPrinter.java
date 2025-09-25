import java.util.List;

public class KHSPrinter {
    public static void printKHS(Mahasiswa s, int semester) {
        List<Enrollment> list = s.getEnrollmentSemester(semester);
        System.out.println("KHS - Semester " + semester);
        System.out.println("Mahasiswa: " + s.getNim() + " - " + s.getNama());
        System.out.println("===============================================================");
        System.out.printf("%-8s %-30s %4s %6s\n", "Kode", "Mata Kuliah", "SKS", "Nilai");
        System.out.println("===============================================================");

        int totalSks = 0;
        double totalPoint = 0.0;
        for (Enrollment enrollment : list) {
            String kode = enrollment.getMatkul().getKode();
            String nama = enrollment.getMatkul().getNama();
            int sks = enrollment.getMatkul().getCredit();
            String nilai = (enrollment.getNilai() == null) ? " - " : enrollment.getNilai().name();
            System.out.printf("%-8s %-30s %4d %6s\n", kode, nama, sks, nilai);
            totalSks += sks;
            totalPoint += enrollment.getNilaiPoint();
        }
        double ip = (totalSks == 0) ? 0.0 : totalPoint / totalSks;
        System.out.println("================================================================");
        System.out.printf("Total SKS: %d, IP: %.2f\n", totalSks, ip);
    }

    public static double hitungIPK(Mahasiswa s) {
        int totalSks = 0;
        double totalPoint = 0.0;
        for (Enrollment e : s.getAllEnrollments()) {
            totalSks += e.getMatkul().getCredit();
            totalPoint += e.getNilaiPoint();
        }
        return (totalSks == 0) ? 0.0 : totalPoint / totalSks;
    }
}
