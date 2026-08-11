import java.util.*;

class Student {

    private String name;
    private int codingScore;
    private int problemSolvingScore;

    public Student(String name, int codingScore, int problemSolvingScore) {
        this.name = name;
        this.codingScore = codingScore;
        this.problemSolvingScore = problemSolvingScore;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return codingScore + problemSolvingScore;
    }

    public void displayStudent(int rank) {
        System.out.println(
            rank + ". " +
            name +
            " | Coding: " + codingScore +
            " | Problem Solving: " + problemSolvingScore +
            " | Total: " + getTotalScore()
        );
    }
}

class RankingSystem {

    private ArrayList<Student> students;

    public RankingSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void generateRanking() {

        students.sort(
            (s1, s2) ->
                Integer.compare(
                    s2.getTotalScore(),
                    s1.getTotalScore()
                )
        );

        System.out.println("Student Skill Ranking:");

        int rank = 1;

        for (Student student : students) {
            student.displayStudent(rank);
            rank++;
        }
    }
}

public class StudentSkillRanking {

    public static void main(String[] args) {

        RankingSystem ranking = new RankingSystem();

        ranking.addStudent(
            new Student("Arjun", 78, 85)
        );

        ranking.addStudent(
            new Student("Meera", 92, 88)
        );

        ranking.addStudent(
            new Student("Rahul", 84, 75)
        );

        ranking.addStudent(
            new Student("Ananya", 89, 94)
        );

        ranking.generateRanking();
    }
}
