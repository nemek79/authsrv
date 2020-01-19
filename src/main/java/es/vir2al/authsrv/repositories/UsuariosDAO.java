package es.vir2al.authsrv.repositories;

import org.springframework.data.repository.CrudRepository;

import es.vir2al.authsrv.models.Usuario;

public interface UsuariosDAO extends CrudRepository<Usuario,Long>{

	/**
	* Consulta personalizada para obtener el usuario a partir de su login
	*/
	public Usuario findByUsername(String username);
	
}
