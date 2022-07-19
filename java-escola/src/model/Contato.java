package model;

/**
 * Classe para armazenar os atributos e métodos do objeto Contato
 * @author Victor Baptista
 * @since 23/02/2021
 */
public class Contato {

	//declarar os atributos do objeto Contato
	private String celular;
	private String email;
	
	//Métodos getters e setters
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
