package share.book.domain.infras;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(); // CORS設定をcorsConfigurationSource()から読み込む
    }

    // CORS設定を行うBean定義
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        // Access-Control-Allow-Origin
        configuration.setAllowedOrigins(List.of("*"));
        // Access-Control-Allow-Methods
        configuration.setAllowedMethods(List.of("*"));
        // Access-Control-Allow-Headers
        configuration.setAllowedHeaders(List.of("*"));
        // Access-Control-Allow-Credentials
        configuration.setAllowCredentials(false);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
