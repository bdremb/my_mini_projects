import java.text.SimpleDateFormat;


public class RedisTest {

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");
    static final RedisStorage redis = new RedisStorage();

    public static void main(String[] args) throws InterruptedException {
        redis.init();

        //регистрация пользователей
        for (int i = 1; i <= 20; i++) {
            redis.addUser(i);
            Thread.sleep(50);
        }

        for (; ; ) {

            for (int i = 0; i < 20; i++) {
                Thread.sleep(300);
                int user;
                if (i == 9 || i == 19) {
                    user = redis.getRandomUser();
                    System.out.println(">Пользователь " + user + " оплатил платную услугу");
                    redis.removeUser(user);
                    Thread.sleep(1000);

                } else {
                    user = redis.getUser();
                    redis.addUser(user);
                }

                System.out.println("- На главной странице показываем пользователя " + user);
            }
        }
    }
}

