import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Mahasiswa {
    private final String nim;
    private final String nama;
    private final List<Enrollment> enrollments =  new ArrayList<>();

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public String getNim() {return nim;}
    public String getNama() {return nama;}

    public void enroll(Matkul matkul, int semester) {
        enrollments.add(new Enrollment(matkul, semester));
    }

    public boolean setNilai(String kodeMatkul, int semester, Nilai nilai) {
        for (Enrollment e : enrollments) {
            if (e.getMatkul().getKode().equalsIgnoreCase(kodeMatkul) && e.getSemester() == semester) {
                e.setNilai(nilai);
                return true;
            }
        }
        return false;
    }

    public List<Enrollment> getEnrollmentSemester(int semester) {
        return enrollments.stream()
                .filter(e -> e.getSemester() == semester)
                .collect(Collectors.toList());
    }

    public List<Enrollment> getAllEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }
}
