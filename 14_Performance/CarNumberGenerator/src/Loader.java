import java.io.BufferedWriter;

public class Loader extends Thread {

    private final char[] letters;
    private final int REGION_CODE;
    private final int START_REGION_CODE;
    private final long START;
    private final BufferedWriter BUFFERED_WRITER;

    public Loader(char[] letters, int START_REGION_CODE,
                  int REGION_CODE, long start, BufferedWriter bufferedWriter) {

        this.letters = letters;
        this.REGION_CODE = REGION_CODE;
        this.START_REGION_CODE = START_REGION_CODE;
        this.START = start;
        this.BUFFERED_WRITER = bufferedWriter;
    }

    @Override
    public void run() {
        try {
            for (int regionCode = START_REGION_CODE; regionCode < REGION_CODE; regionCode++) {

                for (int number = 1; number < 1000; number++) {
                    StringBuilder carNumber = new StringBuilder();
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                carNumber.append(firstLetter).append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(regionCode, 2))
                                        .append('\n');
                            }
                        }
                    }
                    BUFFERED_WRITER.write(carNumber.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - START) + " ms" + "Thread " + Thread.currentThread());
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
