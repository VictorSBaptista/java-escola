package model;

import java.util.ArrayList;

/**
 * Classe responsável por armazenar os atributos e méstodos do objeto Turma
 * @author vicbaptista
 * @since 24/02/2021
 */
public class Turma {

	//declara os atributos do objeto Turma
	private int codigo;
	private int ano;
	private Professor professor;
	private ArrayList<Aluno> alunos;
	private Materia materia;
	
	//Métodos getters e setters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}
