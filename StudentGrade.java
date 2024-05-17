import java.util.Scanner;

public class StudentGrade{

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println(" Enter the number of the subject ");
       int  numSubject = sc.nextInt();

      int totalMarks=0;
       for(int i=1;i<=numSubject;i++){
           System.out.println("Enter marks obtained in subject " +i+" :");
           int marks=sc.nextInt();
           totalMarks +=marks;
       }
       double averagePercentage=(double) totalMarks/numSubject;
       String grade;
       if(averagePercentage>=90){
           grade="0";
       }
       else if(averagePercentage>=80){
           grade= " A";
        }
       else if(averagePercentage>=70){
           grade="B";
       }
       else if (averagePercentage >= 60) {
            grade="C";
       }
       else if (averagePercentage>=50){
           grade="D";
       }
       else {
           grade="F";

       }
        System.out.println(" Total mark: = totalMarks");
        System.out.println("Average Percentage: + averagePercentage");
        System.out.println("Grade: " + grade);
        sc.close();



    }
}