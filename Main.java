public class Main {
    public static void main(String[] args) {
        System.out.println("LMS - Atthallah Kafka");

        SubmissionModule sm = new SubmissionModule();  // membuat objek untuk modul submission
        sm.addSubmission(new Submission("S001", "STU01", "A01", 1000L, "Exam.pdf"));
        sm.addSubmission(new Submission("S002", "STU02", "A01", 1001L, "PersonalAssigment.pdf"));
        sm.addSubmission(new Submission("S003", "STU01", "A01", 1002L, "Answer_cybersecurity.pdf"));

        sm.undoSubmission();    // membatalkan submission terakhir yang masuk
        sm.processSubmission(); // memproses submission pertama dalam antrian

        sm.searchSubmission("S002"); // mencari submission berdasarkan ID

        ExpressionModule em = new ExpressionModule();
        String infix = "5+3*2";  // contoh ekspresi infix
        String postfix = em.infixToPostfix(infix);
        double result = em.evaluatePostfix(postfix);

        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + (int) result);
    }
}