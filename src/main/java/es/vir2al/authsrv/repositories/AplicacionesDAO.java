package es.vir2al.authsrv.repositories;

import org.springframework.data.repository.CrudRepository;

import es.vir2al.authsrv.models.Aplicacion;

public interface AplicacionesDAO extends CrudRepository<Aplicacion,Long> {
	
	public Aplicacion findByClientId(String clientId);

}
