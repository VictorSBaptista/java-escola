package model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto Aluno
 * @author Victor Baptista
 * @since 23/02/2021
 */
public class Aluno {

	//declarando os atributos do objeto Aluno
	private int codidgo;
	private String nome;
	private String cpf;
	private String rg;
	private Endereco endereco;
	private Contato contato;
	
	//Métodos getters e setters
	public int getCodidgo() {
		return codidgo;
	}
	public void setCodidgo(int codidgo) {
		this.codidgo = codidgo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
}
