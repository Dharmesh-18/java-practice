import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student implements Comparable<Student> {
    int id;
    String name;
    int marks;


    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }


    public Student() {
    }

    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getMarks() {
        return marks;
    }


    public void setMarks(int marks) {
        this.marks = marks;
    }

    
}

class SortById implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getId() - s2.getId();
    }
}

class SortByName implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

class SortByMarks implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getMarks() - s2.getMarks();
    }
}

class Test {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "Dharmesh", 90));
        students.add(new Student(2, "John", 80));
        students.add(new Student(3, "Jane", 85));
        students.add(new Student(4, "Doe", 70));
        students.add(new Student(5, "Smith", 95));
        System.out.println("Before sorting:");
        
        for (Student student : students) {
            System.out.println(student.getId() + ", " + student.getName() + ", " + student.getMarks());
        }

        System.out.println("After sorting:");

        Collections.sort(students, new SortById());

        for(Student student : students) {
            System.out.println(student.getId() + ", " + student.getName() + ", " + student.getMarks());
        }

        
    }
}

