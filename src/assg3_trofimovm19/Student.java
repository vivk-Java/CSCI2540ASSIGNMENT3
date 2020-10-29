package assg3_trofimovm19;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final String id;
    private final String name;
    private final String standing;

    private String major;

    public Student(String id, String name, String standing, String major) {
        this.id = id;
        this.name = name;
        this.standing = standing;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStanding() {
        return standing;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return this.id + "," +
                this.name + "," +
                this.standing + "," +
                this.major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Student other = (Student) o;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int compareTo(Student other) {
        return this.id.compareToIgnoreCase(other.id);
    }
}
