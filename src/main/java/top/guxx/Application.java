package top.guxx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.guxx.domain.database.service.DataBaseMCPService;


@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ToolCallbackProvider computerTools(DataBaseMCPService dataBaseMCPService) {
        return MethodToolCallbackProvider.builder().toolObjects(dataBaseMCPService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("mcp server dataBaseMCPService success!");
    }

}