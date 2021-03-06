/*
Solution of Codechef Problem - Game with numbers
Problem Code - GAMENUMB
Link - https://www.codechef.com/problems/GAMENUMB
*/

import java.util.*;
import java.io.*;
import java.lang.*;

class GAMENUMB {

    private static InputStream stream;
    private static byte[] buf = new byte[1024];
    private static int curChar;
    private static int numChars;
    private static SpaceCharFilter filter;
    static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Pair implements Comparable<Pair> {
        int value, count, id;

        public Pair(int value, int count, int id) {
            this.value = value;
            this.count = count;
            this.id = id;
        }

        public int compareTo(Pair o) {
            if (value != o.value) {
                return value - o.value;
            }
            return id - o.id;
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader(System.in);
        int testCases = nI();
        while(testCases-- > 0) {
            int n = nI();
            int k = nI();
            int[] a = new int[n];
            int[] d = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = nI();
            }
            long cardCount = 0, sum = 0;
            TreeSet<Pair> treeSet = new TreeSet<>();
            for(int i = 0; i < n; i++) {
                d[i] = nI();
                treeSet.add(new Pair(a[i], d[i], i));
                cardCount += d[i];
            }
            for(int i = 0; i < k; i++) {
                long b = nL();
                long current = cardCount - b;
                while (current > 0) {
                    Pair p = i % 2 == 0 ? treeSet.pollFirst() : treeSet.pollLast();
                    if (current >= p.count) {
                        current -= p.count;
                    } else {
                        p.count -= current;
                        current = 0;
                        treeSet.add(p);
                    }
                }
                cardCount = b;
            }
            for (Pair o : treeSet) {
                sum += (long) o.value * o.count;
            }
            log.write(String.valueOf(sum) + "\n");
        }
        log.flush();
    }

    public static void InputReader(InputStream stream1) {
        stream = stream1;
    }

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private static boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }

    private static int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    private static int nI() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    private static long nL() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    private static String nS() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    private static String nLi() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    private static boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    private interface SpaceCharFilter {

        public boolean isSpaceChar(int ch);
    }
}