package model;

import java.util.ArrayList;

/**
 * Classe respons�vel por armazenar atributos e m�todos do objeto Professor
 * @author Victor Baptista
 * @since 23/02/2021
 */
public class Professor extends Funcionario{

	//declarando os atributos do objeto professor
	private ArrayList<Materia> listaMaterias;

	//M�todos getters e setters
	public ArrayList<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(ArrayList<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	
}
