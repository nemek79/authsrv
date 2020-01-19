package es.vir2al.authsrv.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.vir2al.authsrv.models.Usuario;
import es.vir2al.authsrv.repositories.UsuariosDAO;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuariosDAO usuariosDAO;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		
		return this.usuariosDAO.findByUsername(username);
		
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuariosDAO.findByUsername(username);
		
		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema");
		}
		
	    List<GrantedAuthority> authorities = usuario.getRoles()
	            .stream()
	            .map(
	              role -> new SimpleGrantedAuthority(role.getRole())
	            )
	            //.peek(authority -> logger.info("Role: "+authority.getAuthority()))
	            .collect(
	              Collectors.toList()
	            );
	    
	    return new User(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
	

}
