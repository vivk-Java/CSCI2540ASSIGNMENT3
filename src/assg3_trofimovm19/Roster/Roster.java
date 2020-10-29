package assg3_trofimovm19.Roster;

import assg3_trofimovm19.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Roster implements RosterInterface {
    private final ArrayList<Student> students;

    private File file;

    private final int WRONG_FILE_FILLING = 1;

    public Roster() {
        students = new ArrayList<Student>();
    }

    @Override
    public void loadData(String filePath) {
        file = new File(filePath);
        students.clear();

        BufferedReader reader;
        FileReader fileReader;

        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                var student = line.split(",");
                if (student.length != 4) {
                    println("Wrong file filling: Student must contain 4 fields.");
                    exit(WRONG_FILE_FILLING);
                }

                addStudent(student[0], student[1], student[2], student[3]);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayRoster() {
        for (Student student : students) {
            println(student);
        }
    }

    @Override
    public Student searchForStudent(String id) {
        return students.stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addStudent(String id,
                              String name,
                              String standing,
                              String major) {

        if (searchForStudent(id) != null) {
            println("Wrong student ID: Student with such ID already exists.");
            return false;
        }

        if (id.isBlank() ||
                name.isBlank() ||
                standing.isBlank() ||
                major.isBlank()) {

            println("Wrong fields filling: Fields can not be empty.");
            return false;
        }

        students.add(new Student(id, name, standing, major));
        return true;
    }

    @Override
    public boolean removeStudent(String id) {
        Student student = searchForStudent(id);
        if (student == null) {
            println("Wrong student ID: Student with such ID does not exist.");
            return false;
        }

        students.remove(student);
        return true;
    }

    @Override
    public ArrayList<Student> getStudentByMajor(String major) {
        ArrayList<Student> majors = new ArrayList<Student>();
        for (Student student : students) {
            if (student.getMajor().equals(major)) {
                majors.add(student);
            }
        }

        return majors;
    }

    @Override
    public void Sort() {
        students.sort(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return extractInt(o1.getId()) - extractInt(o2.getId());
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
    }

    @Override
    public void Save() {
        if (file == null) {
            println("File is not opened.");
            return;
        }

        PrintWriter fileWriter;
        try {
            fileWriter = new PrintWriter(file);
            for (Student student : students) {
                fileWriter.write(student.toString() + "\n");
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            fileWriter = null;
            e.printStackTrace();
        }
    }

    private void println(Object o) {
        System.out.println(o.toString());
    }

    private void exit(int status) {
        System.exit(status);
    }
}
