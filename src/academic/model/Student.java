package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Student extends Person {
    private final String tahun;
    private final String prodi;

    public Student(String _nim, String _nama, String _tahun, String _prodi) {
        super(_nim, _nama);
        this.tahun = _tahun;
        this.prodi = _prodi;
    }

    public String gettahun() {
        return this.tahun;
    }

    public String getprodi () {
        return this.prodi;
    }
 
    @Override
    public String toString() {
        return super.toString() + "|" + this.tahun + "|" + this.prodi;
    }
}