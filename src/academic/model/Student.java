package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

 public class Student extends Person{

    public String year;
    public double gpa;
    public int sks;

    public Student(String id, String name, String year, String studyprogram) {
        super(id, name, studyprogram);
        this.year = year;
        this.studyprogram = studyprogram;
        this.gpa = 0;
        this.sks = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getStudyProgram() {
        return studyprogram;
    }
    
    public void getGpa(double _gpa){
        this.gpa = _gpa;
    }

    public void getSks(int _sks){
        this.sks= _sks;
    }

    public void toStringDetail(Student Std){
        System.out.printf("%s|%s|%s|%s|%.2f|%d\n", this.id, this.name, this.year, this.studyprogram, this.gpa, this.sks);
    }
}