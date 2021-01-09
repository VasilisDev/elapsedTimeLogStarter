package gr.bill.springbootelapsedtimestarter.elapsedtime.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ConditionalOnProperty(prefix = "log.elapsed", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableAspectJAutoProxy
public class ElapsedLogAutoConfiguration {

    @Bean
    public ElapsedLogAspect elapsedLogAspect() {
        return new ElapsedLogAspect();
    }
}
