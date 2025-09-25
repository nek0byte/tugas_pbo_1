public class Matkul {
    private final String kode;
    private final String nama;
    private final int credit;

    public Matkul(String kode, String nama, int credit) {
        this.kode = kode;
        this.nama = nama;
        this.credit = credit;
    }

    public String getKode() {return kode;}
    public String getNama() {return nama;}
    public int getCredit() {return credit;}

    @Override
    public String toString() {
        return kode + "-" + nama + "(" + credit + "sks)";
    }
}
