package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.name;
    }
}
