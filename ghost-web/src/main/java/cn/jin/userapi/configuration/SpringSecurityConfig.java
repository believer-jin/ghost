/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.SecurityConfig.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月18日 上午11:09:27 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月18日    |    Administrator     |     Created 
 */
package cn.jin.userapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年8月18日 上午11:09:27
 * 
 * @author Administrator
 * @version V1.0
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * @Bean
     * 
     * @ConditionalOnMissingBean public AuthenticationProvider
     * authenticationProvider() { return new
     * DefaultJaasAuthenticationProvider(); }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * http .authenticationProvider(authenticationProvider())
         * .csrf().disable()
         * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
         * STATELESS).and() .authorizeRequests()
         * .antMatchers(HttpMethod.OPTIONS, "
         *//**
           * ").permitAll() .antMatchers(HttpMethod.GET, "
           */
        /**
         * ").permitAll() .antMatchers(HttpMethod.POST, "
         */
        /**
         * ").permitAll() .antMatchers(HttpMethod.PUT, "
         */
        /**
         * ").permitAll() .antMatchers(HttpMethod.DELETE, "
         *//**
           * ").permitAll() .anyRequest().authenticated() .and() .httpBasic();
           */
        http.authorizeRequests().antMatchers("/*").permitAll();
        http.csrf().disable();
    }

}
