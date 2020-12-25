package kubiks.aoc.day25;

public class Day25 {
    static final long START_SUBJECT_NUMBER = 7;

    static long transform(long subjectNumber, long loopSize) {
        long value = 1;
        while (loopSize > 0) {
            value *= subjectNumber;
            value %= 20201227;
            loopSize--;
        }
        return value;
    }

    static long reverseEngineerLoopSize(long subjectNumber, long publicKey) {
        long value = 1;
        long loopSize = 0;
        while (value != publicKey) {
            value *= subjectNumber;
            value %= 20201227;
            loopSize++;
        }
        return loopSize;
    }

    public static long crackTheCode(long doorPublicKey, long cardPublicKey) {
        //long doorLoopSize = reverseEngineerLoopSize(START_SUBJECT_NUMBER, doorPublicKey);
        long cardLoopSize = reverseEngineerLoopSize(START_SUBJECT_NUMBER, cardPublicKey);
        long encryptionKey = transform(doorPublicKey, cardLoopSize);
        return encryptionKey;
    }

    public static void main(String[] args) {
        long answer = Day25.crackTheCode(8614349, 8335663);
        System.out.format("Encryption key: %d\n", answer);
    }

}
