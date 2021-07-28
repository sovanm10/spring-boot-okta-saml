package com.saml.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.extensions.saml2.config.SAMLConfigurer;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Value("${security.saml2.metadata-url}")
	private String metadataUrl;
	
	@Value("${server.port}")
	private String port;
	
	@Value("${server.ssl.key-store-password}")
	private String password;
	
	@Value("${server.ssl.key-alias}")
	private String keyAlias;
	
	@Value("${server.ssl.key-store}")
	private String keyStore;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers("/saml*").permitAll()
	    .anyRequest().authenticated()
	    .and().apply(SAMLConfigurer.saml())
	    .serviceProvider()
	    .keyStore()
	    .storeFilePath(keyStore)
	    .password(password)
	    .keyname(keyAlias)
	    .keyPassword(password)
	    .and()
	    .protocol("https")
	    .hostname(String.format("%s:%s","localhost",port) )
	    .basePath("/")
	    .and()
	    .identityProvider()
	    .metadataFilePath(metadataUrl);
	}
	
	

}
