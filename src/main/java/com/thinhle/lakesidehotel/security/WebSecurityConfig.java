package com.thinhle.lakesidehotel.security;

import com.thinhle.lakesidehotel.security.jwt.AuthTokenFilter;
import com.thinhle.lakesidehotel.security.jwt.JwtAuthEntryPoint;
import com.thinhle.lakesidehotel.security.user.HotelUserDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Simpson Alfred
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig {

    // Dịch vụ chi tiết người dùng được sử dụng để tải thông tin người dùng từ cơ sở dữ liệu.
    private final HotelUserDetailsService userDetailsService;

    // Điểm đầu vào cho JWT, được sử dụng để xử lý các lỗi xác thực JWT.
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    // Lọc CORS (Cross-Origin Resource Sharing) được sử dụng để xử lý các yêu cầu từ các nguồn gốc khác nhau.
    @Autowired
    private final CorsFilter corsFilter;

    // Bộ lọc xác thực JWT, được sử dụng để phân tích và xác thực token trong các yêu cầu HTTP.
    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }

    // Bộ mã hóa mật khẩu sử dụng thuật toán BCrypt để mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cung cấp xác thực dựa trên DAO (Data Access Object), sử dụng dịch vụ chi tiết người dùng và bộ mã hóa mật khẩu.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Quản lý xác thực, lấy từ cấu hình xác thực hiện tại.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Chuỗi lọc bảo mật chính, cấu hình cách xử lý bảo mật cho các yêu cầu HTTP.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF (Cross-Site Request Forgery) protection.
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint)) // Cấu hình xử lý lỗi xác thực JWT.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Cấu hình quản lý session không trạng thái.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/rooms/**", "/bookings/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**")
                        .permitAll() // Cho phép truy cập không cần xác thực vào các endpoint nhất định.
                        .requestMatchers("/roles/**").hasRole("ADMIN") // Chỉ cho phép vai trò "ADMIN" truy cập vào các endpoint "/roles/**".
                        .anyRequest().authenticated() // Bắt buộc xác thực với tất cả các yêu cầu khác.
                );

        // Đảm bảo thứ tự của các bộ lọc là đúng
        http.authenticationProvider(authenticationProvider()); // Sử dụng nhà cung cấp xác thực tùy chỉnh.
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class); // Thêm lọc CORS trước bộ lọc xác thực dựa trên tên người dùng và mật khẩu.

        return http.build();
    }



}
