package net.stivka.psp.security;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TomcatConfig {

    @Autowired
    private Environment env;

    @Value("${server.ssl.key-store-password}")
    private String decryptedKeystorePassword;

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
            }
        };

        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);

        connector.setPort(8443);
        connector.setSecure(true);
        connector.setScheme("https");
        connector.setProperty("keyAlias", env.getProperty("server.ssl.key-alias"));
        connector.setProperty("keystorePass", decryptedKeystorePassword);
        connector.setProperty("keystoreType", env.getProperty("server.ssl.key-store-type"));
        connector.setProperty("sslProtocol", "TLS");
        connector.setProperty("SSLEnabled", "true");

        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
