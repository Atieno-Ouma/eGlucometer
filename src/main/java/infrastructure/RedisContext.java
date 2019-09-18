package infrastructure;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class RedisContext {
    private static final String confFilePath = "configurations/redis-conf.yaml";

    private final Config config;
    private final RedissonClient client;

    public RedisContext() {
        try {
            File configurationFile = new File(this.getClass().getClassLoader().getResource(confFilePath).getFile());
            config = Config.fromYAML(configurationFile);
            client = Redisson.create(config);
        }
        catch (IOException ex) {
            throw new RuntimeException("Error while reading redis configuration.", ex);
        }
    }

    public RedissonClient getClient() {
        return client;
    }
}