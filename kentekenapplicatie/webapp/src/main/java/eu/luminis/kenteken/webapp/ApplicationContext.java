package eu.luminis.kenteken.webapp;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan(basePackages = "eu.luminis.kenteken")
@ImportResource("classpath:applicationContext.xml")
@PropertySource("classpath:enwire-services.properties")
public class ApplicationContext {

	@Bean
    public LoggingInInterceptor loggingInInterceptor() {
        final LoggingInInterceptor interceptor = new LoggingInInterceptor();
        interceptor.setPrettyLogging(true);
        return interceptor;
    }

	@Bean
    public JacksonJsonProvider jacksonJsonProvider() {
        final JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        provider.setMapper(mapper);
        return provider;
    }
}
