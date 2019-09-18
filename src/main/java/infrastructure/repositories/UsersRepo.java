
package infrastructure.repositories;

import java.util.Optional;

import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.User;
import infrastructure.RedisContext;

@Component
public class UsersRepo {
    @Autowired
    private RedisContext dbContext;

    public void add(User user) {
        RBucket<User> bucket = dbContext.getClient().getBucket(getRedisKey(user));
        bucket.set(user);
    }

    public Optional<User> get(String emailAddress) {
        RBucket<User> bucket = dbContext.getClient().getBucket(getRedisKey(emailAddress));
        User user = bucket.get();
        return Optional.ofNullable(user);
    }

    private static String getRedisKey(User user) {
        return getRedisKey(user.getEmailAddress());
    }

    private static String getRedisKey(String userEmail) {
        return String.format("user:%s", userEmail);
    }
}