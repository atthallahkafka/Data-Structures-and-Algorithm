import java.util.*;

// class expressionmodule digunakan untuk konversi dan evaluasi ekspresi
public class ExpressionModule {

    // mengecek apakah karakter merupakan operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // untuk menentukan prioritas operator
    private int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    // untuk mengubah ekspresi infix menjadi postfix
    public String infixToPostfix(String exp) {
        StringBuilder output = new StringBuilder();
        Deque<Character> opStack = new ArrayDeque<>(); // stack untuk menyimpan operator sementara
        for (char c : exp.toCharArray()) {
            if (c == ' ') continue;

            // operand langsung dimasukkan ke output
            if (Character.isLetterOrDigit(c)) {
                output.append(c);

            } else if (c == '(') {
                opStack.push(c);

            // memindahkan operator sampai menemukan kurung buka
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    output.append(opStack.pop());
                }

                if (!opStack.isEmpty()) {
                    opStack.pop();
                }

            // melakukan proses operator berdasarkan prioritas
            } else if (isOperator(c)) {
                while (!opStack.isEmpty()
                        && isOperator(opStack.peek())
                        && precedence(opStack.peek()) >= precedence(c)) {
                    output.append(opStack.pop());
                }

                opStack.push(c); // menyimpan operator ke stack
            }
        }

        // memindahkan sisa operator dari stack ke output
        while (!opStack.isEmpty()) {
            output.append(opStack.pop());
        }

        return output.toString();
    }

    // menghitung hasil ekspresi postfix
    public double evaluatePostfix(String exp) {
        
        Deque<Double> numStack = new ArrayDeque<>(); //  menyimpan operand dan hasil sementara

        // membaca setiap karakter pada postfix
        for (char c : exp.toCharArray()) {

            // melewati karakter spasi
            if (c == ' ') continue;

            // mengubah karakter angka menjadi numerik lalu push ke stack
            if (Character.isDigit(c)) {
                numStack.push((double) (c - '0'));

            // melakukan operasi matematika jika menemukan operator
            } else if (isOperator(c)) {

                if (numStack.size() < 2) {
                    throw new IllegalArgumentException("ekspresi tidak valid: " + exp);
                }

                // mengambil dua operand teratas
                double b = numStack.pop();
                double a = numStack.pop();

                switch (c) {
                    case '+':
                        numStack.push(a + b);
                        break;

                    case '-':
                        numStack.push(a - b);
                        break;

                    case '*':
                        numStack.push(a * b);
                        break;

                    case '/':
                        if (b == 0) {
                            throw new ArithmeticException("division by zero");
                        }
                        numStack.push(a / b);
                        break;
                }
            }
        }

        return numStack.pop(); // mengembalikan hasil akhir perhitungan
    }
}