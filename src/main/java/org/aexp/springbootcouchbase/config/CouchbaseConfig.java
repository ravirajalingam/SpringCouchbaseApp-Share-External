package org.aexp.springbootcouchbase.config;

import com.couchbase.client.core.event.consumers.LoggingConsumer;
import com.couchbase.client.core.logging.CouchbaseLogLevel;
import com.couchbase.client.core.metrics.DefaultLatencyMetricsCollectorConfig;
import com.couchbase.client.core.metrics.DefaultMetricsCollectorConfig;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCouchbaseRepositories(basePackages = "org.aexp.springbootcouchbase.mvc")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("#{'${spring.couchbase.bootstrap-hosts}'.split(',')}")
    private List<String> couchbasehosts;

    @Value("${spring.couchbase.bucket.name}")
    private String couchbaseBucketName;

    @Value("${spring.couchbase.bucket.password}")
    private String couchbaseBucketPassword;

    @Value("${spring.couchbase.env.timeouts.connect}")
    private Long couchbaseConnectionTimeout;

    @Autowired
    private CouchbaseEnvironment couchbaseEnvironment;

    @Override
    protected List<String> getBootstrapHosts() {
        return couchbasehosts;
    }

    @Override
    protected String getBucketName() {
        return couchbaseBucketName;
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseBucketPassword;
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        CouchbaseEnvironment couchbaseEnvironment = DefaultCouchbaseEnvironment
                .builder()
                 .defaultMetricsLoggingConsumer(true, CouchbaseLogLevel.TRACE, LoggingConsumer.OutputFormat.JSON_PRETTY)
                 .runtimeMetricsCollectorConfig(DefaultMetricsCollectorConfig.create(30, TimeUnit.SECONDS ))
                 .networkLatencyMetricsCollectorConfig(DefaultLatencyMetricsCollectorConfig.create(30, TimeUnit.SECONDS ))
                .dnsSrvEnabled(false)
                .connectTimeout(couchbaseConnectionTimeout)
                .build();
        return couchbaseEnvironment;
    }

    @PostConstruct
    public void init(){
        couchbaseEnvironment.eventBus().get().subscribe(event ->
            System.out.println(event)
        );
    }
}
