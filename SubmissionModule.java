import java.util.*;

public class SubmissionModule {

    // memproses submission sesuai urutan kedatangan
    private Queue<Submission> submissionQueue = new LinkedList<>();

    // menyimpan riwayat submission, digunakan untuk fitur undo
    private Deque<Submission> undoStack = new ArrayDeque<>();

    // mencari submission berdasarkan submissionID dengan O(1)
    private Map<String, Submission> submissionMap = new HashMap<>();

    // menambahkan submission ke queue, stack, dan map sekaligus
    public void addSubmission(Submission s) {
        submissionQueue.offer(s);
        undoStack.push(s);
        submissionMap.put(s.submissionID, s);
        System.out.println("Submission ditambahkan: " + s.toShortString());
    }

    // mmbatalkan submission terakhir yang masuk
    public void undoSubmission() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada submission untuk di-undo.");
            return;
        }
        Submission last = undoStack.pop();           // ambil submission terakhir dari stack
        submissionQueue.remove(last);
        submissionMap.remove(last.submissionID);
        System.out.println("Submission di-undo: " + last.toShortString());
    }

    // mmproses submission paling awal dari queue
    public void processSubmission() {
        if (submissionQueue.isEmpty()) {
            System.out.println("Queue kosong.");
            return;
        }
        Submission s = submissionQueue.poll(); // dequeue dari depan antrian
        System.out.println("Submission diproses: " + s.toShortString());
    }

    // mncari submission berdasarkan submissionID menggunakan HashMap O(1)
    public Submission searchSubmission(String id) {
        Submission result = submissionMap.get(id); // lookup langsung via hashing
        if (result == null) {
            System.out.println("Submission tidak ditemukan: " + id);
        } else {
            System.out.println("Submission ditemukan: " + result);
        }
        return result;
    }
}