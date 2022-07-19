package model;

import java.util.ArrayList;

/**
 * Classe responsável por armazenar atributos e métodos do objeto Professor
 * @author Victor Baptista
 * @since 23/02/2021
 */
public class Professor extends Funcionario{

	//declarando os atributos do objeto professor
	private ArrayList<Materia> listaMaterias;

	//Métodos getters e setters
	public ArrayList<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(ArrayList<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	
}
