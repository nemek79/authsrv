package es.vir2al.authsrv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import es.vir2al.authsrv.models.Aplicacion;
import es.vir2al.authsrv.repositories.AplicacionesDAO;

@Service
public class AplicacionesServiceImpl implements ClientDetailsService  {

	@Autowired
	private AplicacionesDAO aplicacionesDAO;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		Aplicacion client = this.aplicacionesDAO.findByClientId(clientId);
		
		BaseClientDetails base = new BaseClientDetails(
					client.getClientId(),
					null,
					"read,write,trust",
					"password,authorization_code,refresh_token",
					null
				);
		
		base.setClientSecret(client.getClientSecret());
	  
		
		return base;
		
	}
	

}
