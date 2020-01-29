package es.vir2al.authsrv.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_aplicaciones")
public class Aplicacion implements Serializable  {

	private static final long serialVersionUID = 4990690288035176262L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 32)
	private String clientId;
	@Column(length = 128)
	private String clientSecret;
	@Column(length = 4)
	private String clientShort;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getClientShort() {
		return clientShort;
	}
	public void setClientShort(String clientShort) {
		this.clientShort = clientShort;
	}

}
