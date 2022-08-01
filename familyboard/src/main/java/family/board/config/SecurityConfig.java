package family.board.config;

import family.board.service.MemberApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MemberApiLogicService memberApiLogicService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberApiLogicService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                 // 6
        http.csrf().disable()
                .authorizeRequests() // 6
                .antMatchers("/login.html","/signup.html","/api/board").permitAll()
                .antMatchers(HttpMethod.POST,"/api/board/create").permitAll()
                .antMatchers("/create.html").permitAll()
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 7
                .loginPage("/login.html") // 로그인 페이지 링크
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/api/board")
                .and()
                .logout() // 8
                .logoutSuccessUrl("/login.html") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(false);


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
