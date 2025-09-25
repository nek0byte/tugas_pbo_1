public enum Nilai {
    A(4.0), B(3.0), C(2.0), D(1.0), E(0.0);

    private final double nilai;
    Nilai(double n) { this.nilai = n; }
    public double getNilai() { return nilai; }

    public static Nilai fromString(String s) {
        if (s == null) return null;
        s = s.trim().toUpperCase();
        switch (s) {
            case "A": return A;
            case "B": return B;
            case "C": return C;
            case "D": return D;
            case "E": case "F": return E;
            default: return null;
        }
    }
}
