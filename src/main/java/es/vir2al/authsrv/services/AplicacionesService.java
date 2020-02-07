package es.vir2al.authsrv.services;

import org.springframework.security.oauth2.provider.ClientDetails;

public interface AplicacionesService {

	public ClientDetails loadClientByClientId(String clientId);
	
}
