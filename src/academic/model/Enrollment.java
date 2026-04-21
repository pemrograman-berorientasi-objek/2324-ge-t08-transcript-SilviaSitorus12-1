package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Enrollment {
    private final String ids;
    private final String nims;
    private final String year;
    private final String sems;
    private String grade;
    private String gradelama;
    private String info = "no";
    private int counter = 0;

    public Enrollment(String _ids, String _nims, String _year, String _sems, String _grade) {
        this.ids = _ids;
        this.nims = _nims;
        this.year = _year;
        this.sems = _sems;
        this.grade = _grade;
    }
    public void setgradelama(String gradelama) {
        this.gradelama = gradelama;
    }

    public String getids() {
        return this.ids;
    }

    public String getnims() {
        return this.nims;
    }

    public String getyear() {
        return this.year;
    }

    public String getsems() {
        return this.sems;
    }

    public String getgrade() {
        return this.grade;
    }

    public void setgrade(String _grade) {
        this.grade = _grade;
    }

    public void setremedial(String info) {
        this.info = info;
    }

    public int getcounter() {
        return this.counter;
    }

    public void setcounter() {
        this.counter++;
    }

    @Override
    public String toString() {
        if (info.equals("ya")) {
            return this.ids + "|" + this.nims + "|" + this.year + "|" + this.sems + "|" + this.grade + "(" + this.gradelama + ")";
        } else {
        return this.ids + "|" + this.nims + "|" + this.year + "|" + this.sems + "|" + this.grade;
        }
    }



}