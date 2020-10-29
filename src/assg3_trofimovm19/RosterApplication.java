package assg3_trofimovm19;

import assg3_trofimovm19.Roster.Roster;

import java.util.Objects;
import java.util.Scanner;

public class RosterApplication {

    private final Integer DISPLAY = 1;
    private final Integer SEARCH_BY_ID = 2;
    private final Integer ADD_STUDENT = 3;
    private final Integer REMOVE_STUDENT =4;
    private final Integer SEARCH_BY_MAJOR = 5;
    private final Integer SORT = 6;
    private final Integer SAVE = 7;
    private final Integer EXIT = 8;

    private final String Menu =
            "1.Display the roster\n" +
            "2.Search for a studentby id\n" +
            "3.Add a new student\n" +
            "4.Remove a student\n" +
            "5.Search for students by major\n" +
            "6.Sort and save to file\n" +
            "7.Save to file\n" +
            "8.Exit\n";

    public void Run(String filePath) {
        Roster roster = new Roster();
        roster.loadData(filePath);

        Scanner in = new Scanner(System.in);
        Integer next;
        String nextLine;

        while (true) {
            println(Menu);
            nextLine = in.nextLine();
            while (nextLine.isBlank()) {
                nextLine = in.nextLine();
            }

            try {
                next = Integer.valueOf(nextLine);
            } catch (NumberFormatException e) {
                println("Unknown menu item.\n");
                continue;
            }

            if (Objects.equals(next, DISPLAY)) {
                displayRoster(roster);
            } else if (Objects.equals(next, SEARCH_BY_ID)) {
                searchById(roster, in);
            } else if (Objects.equals(next, ADD_STUDENT)) {
                addStudent(roster, in);
            } else if (Objects.equals(next, REMOVE_STUDENT)) {
                removeStudent(roster, in);
            } else if (Objects.equals(next, SEARCH_BY_MAJOR)) {
                searchByMajor(roster, in);
            } else if (Objects.equals(next, SORT)) {
                sort(roster);
            } else if (Objects.equals(next, SAVE)) {
                save(roster);
            } else if (Objects.equals(next, EXIT)) {
                break;
            } else {
                println("Unknown menu item.");
            }

            println();
        }
    }

    private void displayRoster(Roster roster) {
        roster.displayRoster();
    }

    private void searchById(Roster roster, Scanner in) {
        print("Print ID: ");
        String id = in.next();

        Student student = roster.searchForStudent(id);
        if (student != null) {
            println(student.toString());
        } else {
            println("Wrong student ID: " +
                    "Student with such ID does not exist.");
        }
    }

    private void searchByMajor(Roster roster, Scanner in) {
        print("Print major: ");
        String major = in.next();

        var students = roster.getStudentByMajor(major);
        for (Student student : students) {
            println(student);
        }

        if (students.size() == 0) {
            println("None.");
        }
    }

    private void addStudent(Roster roster, Scanner in) {
        print("Print ID: ");
        String id = in.next();

        Student student = roster.searchForStudent(id);
        if (student != null) {
            println("Wrong student ID: Student with such ID already exists.");
        } else {
            print("Print name: ");
            String name = in.next();

            print("Print standing: ");
            String standing = in.next();

            print("Print major: ");
            String major = in.next();

            if (roster.addStudent(id, name, standing, major)) {
                println("Success.");
                roster.Save();
            } else {
                println("Cancelled.");
            }
        }
    }

    private void removeStudent(Roster roster, Scanner in) {
        print("Print ID: ");
        String id = in.next();

        if (roster.removeStudent(id)) {
            println("Success.");
            roster.Save();
        } else {
            println("Cancelled.");
        }
    }

    private void sort(Roster roster) {
        roster.Sort();
        roster.Save();
    }

    private void save(Roster roster) {
        roster.Save();
    }

    private void print(Object o) {
        System.out.print(o.toString());
    }

    private void println(Object o) {
        System.out.println(o.toString());
    }

    private void println() {
        System.out.println();
    }
}
