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
        CorsConfiguration configuration = new CorsConfiguration();
        // 全てのオリジンを許可
        configuration.setAllowedOrigins(List.of("*"));
        // 全てのメソッドを許可（GET, POST, PUT, DELETE）
        configuration.setAllowedMethods(List.of("*"));
        // 全てのリクエストヘッダを許可
        configuration.setAllowedHeaders(List.of("*"));
        // 認証不要
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
