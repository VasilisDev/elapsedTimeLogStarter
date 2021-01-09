package gr.bill.springbootelapsedtimestarter.elapsedtime.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "log.elapsed")
@Getter
@Setter
public class ElapsedLogProperties {
    private boolean enable;
}
