import java.text.DecimalFormat;


public class Hospital {
    public static final int NUMBER_OF_PATIENTS = 30;
    public static final float MIN_TEMP_OF_PATIENT = 32.0f;
    public static final float MAX_TEMP_OF_PATIENT = 40.0f;
    public static final float MAX_TEMP_OF_HEALTHY_PATIENT = 36.9f;
    public static final float MIN_TEMP_OF_HEALTHY_PATIENT = 36.2f;

    public static void main(String[] args) {

        float[] temperature = new float[NUMBER_OF_PATIENTS];
        float sumTemp = 0;
        int countHealth = 0;
        DecimalFormat dfTemp = new DecimalFormat("#.0");
        DecimalFormat dfMiddleTemp = new DecimalFormat("#.00");

        for (int i = 0; i < temperature.length; i++) {
            float a = (float) (Math.random() * (MAX_TEMP_OF_PATIENT - MIN_TEMP_OF_PATIENT)) + MIN_TEMP_OF_PATIENT;
            temperature[i] = a;
        }

        System.out.print("Температуры пациентов:");
        for (float t : temperature) {
            System.out.print(" " + dfTemp.format(t).replace(",", "."));
            sumTemp += t;
            if (t <= MAX_TEMP_OF_HEALTHY_PATIENT && t >= MIN_TEMP_OF_HEALTHY_PATIENT) {
                countHealth++;
            }
        }

        String middleTemp = dfMiddleTemp.format(sumTemp / temperature.length).replace(",", ".");
        System.out.println("\nСредняя температура: " + middleTemp);
        System.out.println("Количество здоровых: " + countHealth);
    }
}
