package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Lecturer extends Person{
    private final String inisial;
    private final String email;
    private final String prodi_dosen;

    public Lecturer(String _nidn, String _nama_dosen, String _inisial, String _email, String _prodi_dosen){
        super(_nidn, _nama_dosen);
        this.inisial = _inisial;
        this.email = _email;
        this.prodi_dosen = _prodi_dosen;
    }

    public String getinisial() {
        return this.inisial;
    }

    public String getemail() {
        return this.email;
    }

    public String getprodi_dosen() {
        return this.prodi_dosen;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.inisial + "|" + this.email + "|" + this.prodi_dosen;
    }
}