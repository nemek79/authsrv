package es.vir2al.authsrv.services;

import es.vir2al.authsrv.models.Usuario;

public interface UsuarioService {

	public Usuario findByUsername(String username);
	
}
