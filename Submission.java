public class Submission {

    String submissionID;
    String studentID;
    String assignmentID;
    long timestamp; // waktu saat submission dilakukan
    String fileOrAnswer;

    // konstruktor untuk membuat objek submission baru
    public Submission(String submissionID, String studentID,
                      String assignmentID, long timestamp, String fileOrAnswer) {
        this.submissionID = submissionID;
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.timestamp = timestamp;
        this.fileOrAnswer = fileOrAnswer;
    }

    // mengembalikan format pendek anya submissionID
    public String toShortString() {
        return submissionID;
    }

    // mengembalikan format lengkap untuk searchSubmission
    @Override
    public String toString() {
        return "Submission{submissionId='" + submissionID + "', studentId='" + studentID
               + "', assignmentId='" + assignmentID + "', timpestamp=" + timestamp
               + ", fileContent='" + fileOrAnswer + "'}";
    }
}