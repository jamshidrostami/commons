package org.bardframework.commons.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface Configurator {
    void configure(HttpSecurity httpSecurity);
}
