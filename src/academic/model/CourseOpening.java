package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class CourseOpening {
    private final String id;
    private final String year;
    private final String sems;
    private final String inisial;

    public CourseOpening(String _id, String _year, String _sems, String _inisial) {
        this.id = _id;
        this.year = _year;
        this.sems = _sems;
        this.inisial = _inisial;
    }

    public String getid() {
        return this.id;
    }

    public String getyear() {
        return this.year;
    }

    public String getsems() {
        return this.sems;
    }

    public String getinisial() {
        return this.inisial;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.year + "|" + this.sems;
    }
}