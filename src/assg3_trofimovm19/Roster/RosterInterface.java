package assg3_trofimovm19.Roster;

import assg3_trofimovm19.Student;

import java.util.ArrayList;

public interface RosterInterface {
    void loadData(String path);
    void displayRoster();
    Student searchForStudent(String id);
    boolean addStudent(String id, String name, String standing, String major);
    boolean removeStudent(String id);
    ArrayList<Student> getStudentByMajor(String major);
    void Sort();
    void Save();
}
