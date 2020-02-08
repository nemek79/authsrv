package es.vir2al.authsrv.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import es.vir2al.authsrv.models.Usuario;
import es.vir2al.authsrv.services.UsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private UsuarioService usuarioSRV;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Usuario usuario = usuarioSRV.findByUsername(authentication.getName());
		
		
		Map<String, Object> info = new HashMap<>();
		
		//TODO: revisar la información que se introduce en el token
		/*
		info.put("info_adicional","Hola que tal! ".concat(authentication.getName()));
		info.put("nombre_usuario",usuario.getUsername() + ":" + usuario.getId());
		info.put("nombre",usuario.getNombre());
		info.put("apellidos",usuario.getApellidos());
		info.put("email",usuario.getEmail());
		*/
		
		//info.put("email2", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		//for get cliente Id
		//System.out.println(authentication.getOAuth2Request().getClientId());
		
		return accessToken;

	}
}
