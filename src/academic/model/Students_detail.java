package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class Students_detail {
    
    private String id;
    private String name;
    private int year;
    private String major;
    private float gpa; 
    private int credit;

  
    public Students_detail(String id, String name, int year, String major,float gpa,int credit) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.major = major;
        this.gpa= gpa;
        this.credit=credit;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getMajor() {
        return major;
    }
    public float getGpa() {
        return gpa;
    }
    public int getcredit() {
        return credit;
    }
    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + major+ "|" + gpa + "|" + credit;
    }

}
