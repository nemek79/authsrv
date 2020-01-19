package es.vir2al.authsrv.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	/**
	 * Configura los permisos en el authentication
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	
		security.tokenKeyAccess("permitAll()") // permiso a cualquier usuario a poder obtener un token -> /oauth/token/
				.checkTokenAccess("isAuthenticated()"); // validar el token
	}
	
	/**
	 * Configura las aplicaciones (fronts) que tienen acceso a este servidor
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	
		//TODO: registrar el acceso de cada aplicacion
		clients.inMemory().withClient("testapp")
		  .secret(this.passwordEncoder.encode("12345"))
		  .scopes("read","write")
		  .authorizedGrantTypes("password","refresh_token")
		  .accessTokenValiditySeconds(3600) // 1 hora - va en segundos
		  .refreshTokenValiditySeconds(3600);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	
		// agregar la información no-estandar que queremos meter en el token
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		endpoints.authenticationManager(this.authenticationManager)
				.tokenStore(tokenStore()) // se puede omitir, en tal caso hace lo mismo automáticamente
				.accessTokenConverter(this.accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain);
	}
	
	// se puede omitir si no se especifica en el endpoints
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(this.accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
		// Dos formas de validar la firma del token
		
	    // asignar MAC a la firma del token para validar
	    jwtAccessTokenConverter.setSigningKey(JwtConfig.LLAVE_SECRETA);
		
		// Más segura
		/*
		jwtAccessTokenConverter.setSigningKey(JwtCofing.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtCofing.RSA_PUBLICA);
		*/
		
		return jwtAccessTokenConverter;
	
	}
	
}
