package ps.demo.uploadspringbatch.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.UserVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Slf4j
@Configuration
public class EmbeddedMongoConfig {

    @Value("${embedded.mongo.port:27017}")
    private int mongoPort;

    @Bean
    public MongoServer mongodServer() {
        MemoryBackend memoryBackend = new MemoryBackend();
        memoryBackend.openOrCreateDatabase("test");
        MongoServer server = new MongoServer(memoryBackend);
        server.bind("localhost", mongoPort);
        return server;
    }

    @EventListener
    public void handleMemoryMongoServerClose(@Autowired MongoServer mongoServer) {
        log.info("mongoServer.shutdown ..., {}", mongoServer);
        if (mongoServer != null) {
            mongoServer.shutdownNow();
        }
    }

    @Bean
    public MongoClient mongoClient(@Autowired MongoServer mongoServer) {
        return MongoClients.create("mongodb://localhost:"+mongoPort+"/test?retryWrites=false");
    }

    @Bean
    public MongoTemplate mongoTemplate(@Autowired MongoClient mongoClient) throws Exception {
        return new MongoTemplate(mongoClient, "test");
    }

}