package es.vir2al.authsrv.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import es.vir2al.authsrv.models.Usuario;
import es.vir2al.authsrv.services.UsuarioService;

@ContextConfiguration(classes = UsuarioService.class)
public class UsuarioServiceShould {

	@Autowired
	private UsuarioService usuarioSRV;
	
	@Test
	@DisplayName(value = "Test1 ->prueba .")
	public void testShould() {

	}
	
}
