package model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto Materia
 * @author Victor Baptista
 * @since 23/02/2021
 */
public class Materia {

	//declarando os atributos do objeto Materia
	private int codigo;
	private String nome;
	
	//Métodos getters e setters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
