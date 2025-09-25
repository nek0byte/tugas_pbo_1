public class Enrollment {
    private final Matkul matkul;
    private final int semester;
    private Nilai nilai;

    public Enrollment(Matkul matkul, int semester) {
        this.matkul = matkul;
        this.semester = semester;
    }

    public Matkul getMatkul() { return matkul;}
    public int getSemester() { return semester;}
    public Nilai getNilai() { return nilai; }
    public void setNilai(Nilai nilai) { this.nilai = nilai; }

    public double getNilaiPoint() {
        return (nilai == null) ? 0.0 : nilai.getNilai() * matkul.getCredit();
    }
}
