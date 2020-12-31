import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<Integer> onlineUsers;

    private final static String KEY = "USERS";

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    int getRandomUser() {
        Random random = new Random();
        int j = onlineUsers.count(1, true, Integer.MAX_VALUE, true);
        return random.nextInt(j);
    }

    void removeUser(int user) {
        onlineUsers.addScore(user, onlineUsers.size());
    }

    int getUser() {
        return onlineUsers.pollFirst();
    }

    void addUser(int user_id) {
        onlineUsers.add(getTs(), user_id);
    }

    int addUserScore(int user_id) {
        onlineUsers.addScore(user_id, onlineUsers.size());
        return user_id;
    }

}