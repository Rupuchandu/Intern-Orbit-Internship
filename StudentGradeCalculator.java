import java.util.Scanner;

public class StudentGradeCalculator {

    // Function to calculate grade based on average percentage
    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // Function to calculate total marks, average percentage, and grade
    public static void calculateGradeReport(double[] marks) {
        double totalMarks = 0;
        int numSubjects = marks.length;

        // Sum of all subject marks
        for (int i = 0; i < numSubjects; i++) {
            totalMarks += marks[i];
        }

        // Calculate average percentage
        double averagePercentage = (totalMarks / (numSubjects * 100)) * 100;

        // Get grade based on average percentage
        String grade = calculateGrade(averagePercentage);

        // Display the results
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input marks for each subject
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();

        double[] marks = new double[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextDouble();
        }

        // Calculating total marks, average percentage, and grade
        calculateGradeReport(marks);

        scanner.close();
    }
}
