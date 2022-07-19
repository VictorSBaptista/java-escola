package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Contato;
import model.Endereco;
import model.Materia;
import model.Professor;
import model.Turma;
import util.Mensagem;
import util.Rotulo;
import util.Valida;

/**
 * Classe responsável por controlar o sistema de gerenciamento para cadastro de
 * aluno, turmas, matérias e professores.
 * 
 * @author Victor Baptista
 *
 */
public class GerenciaEscola {

	// declarando as listas para armazenar os conteúdos do sistema( ALUNO E
	// PROFESSORES)
	private ArrayList<Aluno> alunos;
	private ArrayList<Professor> professores;
	private ArrayList<Materia> materias;
	private ArrayList<Turma> turmas;

	// Construtor da classe
	public GerenciaEscola() {

		// instancializa os objetos
		alunos = new ArrayList<Aluno>();
		professores = new ArrayList<Professor>();
		materias = new ArrayList<Materia>();
		turmas = new ArrayList<Turma>();

		// inicializa o menu principal
		menuPrincipal();
	}

	// Método para criar um menu de opções
	public void menuPrincipal() {

		// variável auxiliar de mensagem
		String menu = "INFORME A OPÇÃO DESEJADA\n\n" + "1-CADASTRAR MATÉRIA\n" + "2-CADASTRAR PROFESSOR\n"
				+ "3-CADASTRAR ALUNO\n" + "4-CADASTRAR TURMAS\n" + "5-LISTAR ALUNOS\n" + "6-LISTAR PROFESSORES\n"
				+ "7-LISTAR TURMA\n" + "8-CONSULTAR TURMA\n" + "9-SAIR DO SISTEMA\n";

		// looping de processamento do menu principal
		while (true) {
			try {
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
				processamentoPrincipal(opcao);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.sistemaEscola, 0);
			}
		}
	}

	// Processa o menu e as escolhas do usuário
	public void processamentoPrincipal(int opcao) {

		// acessa o setor do menu baseado na escolha do usuário
		switch (opcao) {
		case 1:
			cadastrarMateria();
			break;

		case 2:
			cadastrarProfessor();
			break;

		case 3:
			cadastrarAluno();
			break;

		case 4:
			cadastrarTurma();
			break;

		case 5:
			listarAlunos();
			break;

		case 6:
			listarProfessores();
			break;

		case 7:
			listarTurmas();
			break;

		case 8:
			consultarTurma();
			break;

		case 9:
			sairSistema();
			break;

		default:
			JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.sistemaEscola, 0);
			break;
		}
	}

	// método para cadastrar matéria
	public void cadastrarMateria() {

		// Adiciona uma matéria na lista de disponíveis
		materias.add(getMateria());

		// informa ao usuário sucesso ao cadastrar as matérias
		JOptionPane.showMessageDialog(null, Mensagem.materiaCadastrada, Rotulo.cadastroMateria, 1);
	}

	// método para cadastrar professor
	public void cadastrarProfessor() {

		// adiciona um professor a lista de disponíveis e informa o usuário se o
		// cadastro ocorreu com sucesso ou não
		if (materias.size() > 0) {
			professores.add(getProfessor());
			JOptionPane.showMessageDialog(null, Mensagem.professorCadastrado, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroProfessor, Rotulo.cadastroProfessor, 2);
		}
	}

	// método para cadastrar aluno
	public void cadastrarAluno() {

		// adiciona um aluno
		alunos.add(getAluno());

		// informa o usuário sucesso ao cadastrar o aluno
		JOptionPane.showMessageDialog(null, Mensagem.alunoCadastrado, Rotulo.cadastroAluno, 1);
	}

	// método para cadastrar turma
	public void cadastrarTurma() {

		// adiciona uma turma e informa ao usuário sucesso ou fracasso do cadastro da
		// turma
		if ((professores.size() > 0) && (alunos.size() > 0)) {
			turmas.add(getTurma());
			JOptionPane.showMessageDialog(null, Mensagem.turmaCadastrada, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroTurma, Rotulo.cadastroTurma, 2);
		}
	}

	// Método para retornar um objeto do tipo turma
	private Turma getTurma() {

		// declara e instancializa um objeto Turma
		Turma turma = new Turma();

		// Atribui valores aos atributos do objeto
		turma.setCodigo(getCodigoTurma());
		turma.setAno(getAnoTurma());

		//
		turma.setProfessor(getProfessorTurma());
		turma.setMateria(getMateriaTurma());
		turma.setAlunos(getAlunosTurma());

		// retorna o objeto
		return turma;
	}

	// método para listar alunos cadastrados
	public void listarAlunos() {

		// variáveis auxiliares
		String mensagem = "Alunos cadastrados\n";
		boolean existe = false;

		// varre a lista de alunos existente e caso existir os atribui na variável
		// auxiliar
		for (Aluno aluno : alunos) {
			existe = true;
			mensagem += "\nCódigo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// exibe a mensagem auxiliar contendo os alunos exstentes ou exibe que não há
		// alunos cadastrados
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroAluno, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.alunosVazio, Rotulo.cadastroAluno, 2);
		}
	}

	// método para listar alunos cadastrados
	public void listarProfessores() {

		// variáveis auxiliares
		String mensagem = "Professores cadastrados\n";
		boolean existe = false;

		// varre a lista de professores existente e caso existir os atribui na variável
		// auxiliar
		for (Professor professor : professores) {
			existe = true;
			mensagem += "\nCódigo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		// exibe a mensagem auxiliar contendo os professores existentes ou exibe que não
		// há professores cadastrados
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.professoresVazio, Rotulo.cadastroProfessor, 2);
		}
	}

	// método para listar alunos cadastrados
	public void listarTurmas() {

		// variáveis auxiliares
		String mensagem = "Turmas cadastradas\n";
		boolean existe = false;

		// varre a lista de turmas existente e caso existir as atribui na variável
		// auxiliar
		for (Turma turma : turmas) {
			existe = true;
			mensagem += "\nCódigo: " + turma.getCodigo() + " - " + turma.getAno() + " - "
					+ turma.getMateria().getNome();
		}

		// exibe a mensagem auxiliar contendo as turmas existentes ou exibe que não há
		// turmas cadastradas
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.turmasVazio, Rotulo.cadastroTurma, 2);
		}
	}

	// método para consultar uma turma específica
	public void consultarTurma() {

		// declara e instancializa um objeto Turma
		Turma turma = getConsultarTurma();

		// declara variável auxiliar e atribui para ela os atributos do objeto Turma
		String mensagem = "Detalhes da Turma\n";
		mensagem += "\nCódigo: " + turma.getCodigo();
		mensagem += "\nAno: " + turma.getAno();
		mensagem += "\nProfessor: " + turma.getProfessor().getNome();
		mensagem += "\nMateria: " + turma.getMateria().getNome();
		mensagem += "\n\nAlunos Matriculados:";

		// varre o vetor de alunos do objeto turma e os atribui na variável auxiliar
		for (Aluno aluno : turma.getAlunos()) {
			mensagem += "\nCódigo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// exibe a variável auxliar com todos os dados do objeto Turma
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.consultaTurma, 1);

	}

	// método para sair do sistema
	public void sairSistema() {

		// Mensagem de confirmação para sair do programa
		int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sistema?", "Atenção", JOptionPane.YES_OPTION,
				JOptionPane.CANCEL_OPTION);

		// caso o usuário escolha a opção sim, o programa finaliza
		if (opcao == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// método para retornar os atributos do aluno
	public Aluno getAluno() {
		// declara e instancializa objetos do tipo Aluno, Endereço e Contato
		Aluno aluno = new Aluno();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		// atribui valores ao objeto Aluno
		aluno.setCodidgo(getCodigoAluno());
		aluno.setNome(getNomeAluno());
		aluno.setCpf(getCpfAluno());
		aluno.setRg(getRgAluno());

		// atribui valores ao objeto Endereço
		endereco.setLogradouro(getLogradouro());
		endereco.setNome(getNomeLogradouro());
		endereco.setNumero(getNumero());
		endereco.setComplemento(getComplemento());
		endereco.setBairro(getBairro());
		endereco.setCidade(getCidade());
		endereco.setEstado(getEstado());
		endereco.setCep(getCep());

		// atribui valores ao objeto Contato
		contato.setCelular(getCelular());
		contato.setEmail(getEmail());

		// atribui valores do objeto Endereço e Contato ao objeto Aluno
		aluno.setEndereco(endereco);
		aluno.setContato(contato);

		// retorna o objeto Aluno
		return aluno;
	}

	// método para reatornar os atributos do professor
	private Professor getProfessor() {
		// declara e instancializa objetos do tipo Professor, Endereço e Contato
		Professor professor = new Professor();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		// atribui valores ao objeto Professor
		professor.setCodigo(getCodigoProfessor());
		professor.setNome(getNomeProfessor());
		professor.setCpf(getCpfProfessor());
		professor.setRg(getRgProfessor());
		professor.setSalario(getSalario());

		// atribui valores ao objeto Endereço
		endereco.setLogradouro(getLogradouro());
		endereco.setNome(getNomeLogradouro());
		endereco.setNumero(getNumero());
		endereco.setComplemento(getComplemento());
		endereco.setBairro(getBairro());
		endereco.setCidade(getCidade());
		endereco.setEstado(getEstado());
		endereco.setCep(getCep());

		// atribui valores ao objeto Contato
		contato.setCelular(getCelular());
		contato.setEmail(getEmail());

		// Atribui valores do objeto Endereço e Contato ao objeto Professor
		professor.setEndereco(endereco);
		professor.setContato(contato);

		// Atribui valores do tipo Materia ao objeto Professor
		professor.setListaMaterias(getMaterias());

		// retorna o objeto professor
		return professor;
	}

	// Método para retornar um objeto materia
	private Materia getMateria() {

		// declara e instancializa objeto Materia
		Materia materia = new Materia();

		// atribui valores ao objeto Materia
		materia.setCodigo(getCodigoMateria());
		materia.setNome(getNomeMateria());

		// retorna o objeto materia
		return materia;
	}

	// Método para retornar o código de um objeto do tipo Materia
	private int getCodigoMateria() {
		// variáveis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o código informado é válido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o código informado
				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroMateria, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroMateria, 0);
				execute = true;
			}
		}

		// retorna o código do objeto Materia
		return codigo;
	}

	// Método para retornar o nome de um objeto do tipo Materia
	private String getNomeMateria() {

		// variáveis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado é válido
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);

			// valida o nome informado
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroMateria, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o nome do objeto Materia
		return nome;
	}

	// Método para retornar o código de um objeto do tipo Aluno
	private int getCodigoAluno() {
		// variáveis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o código informado é válido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o código informado
				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroAluno, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroAluno, 0);
				execute = true;
			}
		}

		// retorna o código informado
		return codigo;
	}

	// Método para retornar o nome de um objeto do tipo Aluno
	private String getNomeAluno() {
		// variáveis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado é valido
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);

			// valida o nome informado
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o nome informado
		return nome;
	}

	// Método para retornar o CPF de um objeto do tipo Aluno
	private String getCpfAluno() {
		// variáveis auxiliares
		boolean execute = true;
		String cpf = "";

		// verifica se o cpf informado é válido
		while (execute) {
			cpf = JOptionPane.showInputDialog(Mensagem.informeCpf);

			// valida o cpf informado
			if (Valida.isEmptyOrNull(cpf)) {
				JOptionPane.showMessageDialog(null, Mensagem.cpfVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o cpf do objeto Aluno
		return cpf;
	}

	// Método para retornar o RG de um objeto do tipo Aluno
	private String getRgAluno() {
		// variáveis auxiliares
		boolean execute = true;
		String rg = "";

		// verifica se o rg informado é válido
		while (execute) {
			rg = JOptionPane.showInputDialog(Mensagem.informeRg);

			// valida o RG informado
			if (Valida.isEmptyOrNull(rg)) {
				JOptionPane.showMessageDialog(null, Mensagem.rgVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o RG informado
		return rg;
	}

	// Método para retornar o logradouro de um objeto do tipo Endereço
	private String getLogradouro() {
		// variáveis auxiliares
		boolean execute = true;
		String logradouro = "";

		// verifica se o logradouro informado é valido
		while (execute) {
			logradouro = JOptionPane.showInputDialog(Mensagem.informeLogradouro);

			// valida o logradouro informado
			if (Valida.isEmptyOrNull(logradouro)) {
				JOptionPane.showMessageDialog(null, Mensagem.logradouroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o logradouro iinformado
		return logradouro;
	}

	// Método para retornar o nome do logradouro de um objeto do tipo Endereço
	private String getNomeLogradouro() {
		// variáveis auxiliares
		boolean execute = true;
		String nomeLogradouro = "";

		// verifica se o nome do logradouro é válido
		while (execute) {
			nomeLogradouro = JOptionPane.showInputDialog(Mensagem.informeNomeLogradouro);

			// valida o nome do logradouro
			if (Valida.isEmptyOrNull(nomeLogradouro)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeLogradouroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o nome do logradouro informado
		return nomeLogradouro;
	}

	// Método para retornar o número de um objeto do tipo Endereço
	private int getNumero() {
		// variáveis auxiliares
		boolean execute = true;
		int numero = 0;

		// verifica se o número informado é válido
		while (execute) {
			try {
				numero = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeNumero));

				// valida o núemro informado
				if (Valida.isIntZero(numero)) {
					JOptionPane.showMessageDialog(null, Mensagem.numeroVazio, Rotulo.cadastroEndereco, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.numeroInvalido, Rotulo.cadastroEndereco, 0);
				execute = true;
			}
		}

		// retorna o número informado
		return numero;
	}

	// Método para retornar o complemento de um objeto do tipo Endereço
	private String getComplemento() {
		// variável auxiliar
		String complemento = "";

		// recebe o complemento do usuário
		complemento = JOptionPane.showInputDialog(Mensagem.informeComplemento);

		// retorna o complemento informado
		return complemento;
	}

	// Método para retornar o bairro de um objeto do tipo Endereço
	private String getBairro() {
		// variáveis auxiliares
		boolean execute = true;
		String bairro = "";

		// verifica se o bairro informado é válido
		while (execute) {
			bairro = JOptionPane.showInputDialog(Mensagem.informeBairro);

			// valida o bairro informado
			if (Valida.isEmptyOrNull(bairro)) {
				JOptionPane.showMessageDialog(null, Mensagem.bairroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o bairro informado
		return bairro;
	}

	// Método para retornar a cidade de um objeto do tipo Endereço
	private String getCidade() {
		// variáveis auxiliares
		boolean execute = true;
		String cidade = "";

		// verifica se a cidade informada é válida
		while (execute) {
			cidade = JOptionPane.showInputDialog(Mensagem.informeCidade);

			// valida a cidade informada
			if (Valida.isEmptyOrNull(cidade)) {
				JOptionPane.showMessageDialog(null, Mensagem.cidadeVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna a cidade informada
		return cidade;
	}

	// Método para retornar o estado de um objeto do tipo Endereço
	private String getEstado() {
		// variáveis auxiliares
		boolean execute = true;
		String estado = "";

		// verifica se o estado informado é válido
		while (execute) {
			estado = JOptionPane.showInputDialog(Mensagem.informeEstado);

			// valida o estado informado
			if (Valida.isEmptyOrNull(estado)) {
				JOptionPane.showMessageDialog(null, Mensagem.estadoVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o estado informado
		return estado;
	}

	// Método para retornar o CEP de um objeto do tipo Endereço
	private String getCep() {
		// variáveis auxiliares
		boolean execute = true;
		String cep = "";

		// verifica se o CEP informado é válido
		while (execute) {
			cep = JOptionPane.showInputDialog(Mensagem.informeCep);

			// valida o CEP informado
			if (Valida.isEmptyOrNull(cep)) {
				JOptionPane.showMessageDialog(null, Mensagem.cepVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o CEP informado
		return cep;
	}

	// Método para retornar o celurar de um objeto do tipo Contato
	private String getCelular() {
		// variáveis auxiliares
		boolean execute = true;
		String celular = "";

		// verifica se o celular informado é válido
		while (execute) {
			celular = JOptionPane.showInputDialog(Mensagem.informeCelular);

			// valida o celular informado
			if (Valida.isEmptyOrNull(celular)) {
				JOptionPane.showMessageDialog(null, Mensagem.celularVazio, Rotulo.cadastroContato, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o celular iformado
		return celular;
	}

	// Método para retornar o email de um objeto do tipo Contato
	private String getEmail() {
		// variáveis auxiliares
		boolean execute = true;
		String email = "";

		// verifica se o email informado é válido
		while (execute) {
			email = JOptionPane.showInputDialog(Mensagem.informeEmail);

			// valida o email informado
			if (Valida.isEmptyOrNull(email)) {
				JOptionPane.showMessageDialog(null, Mensagem.emailVazio, Rotulo.cadastroContato, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o email informado
		return email;
	}

	// Método para retornar o código de um objeto do tipo Professor
	private int getCodigoProfessor() {
		// variáveis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o código informado é válido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o código informado
				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroProfessor, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroProfessor, 0);
				execute = true;
			}
		}

		// retorna o código informado
		return codigo;
	}

	// Método para retornar o nome de um objeto do tipo Professor
	private String getNomeProfessor() {
		// variáveis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado é válido
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);

			// valida o nome informado
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o nome informado
		return nome;
	}

	// Método para retornar o CPF de um objeto do tipo Professor
	private String getCpfProfessor() {
		// variáveis auxiliares
		boolean execute = true;
		String cpf = "";

		// verifica se o CPF informado é válido
		while (execute) {
			cpf = JOptionPane.showInputDialog(Mensagem.informeCpf);

			// valida o CPF informado
			if (Valida.isEmptyOrNull(cpf)) {
				JOptionPane.showMessageDialog(null, Mensagem.cpfVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o CPF informado
		return cpf;
	}

	// Método para retornar o PG de um objeto do tipo Professor
	private String getRgProfessor() {
		// variáveis auxiliares
		boolean execute = true;
		String rg = "";

		// verifica se o RG informado é válido
		while (execute) {
			rg = JOptionPane.showInputDialog(Mensagem.informeRg);

			// valida o RG informado
			if (Valida.isEmptyOrNull(rg)) {
				JOptionPane.showMessageDialog(null, Mensagem.rgVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}

		// retorna o RG informado
		return rg;
	}

	// Método para retornar a lista de materias de um objeto do tipo Materia
	private ArrayList<Materia> getMaterias() {

		// declara e instancializa uma lista de objeto do tipo Materia
		ArrayList<Materia> materias = new ArrayList<Materia>();

		// variáveis auxiliares
		String mensagem = "Matérias cadastradas\n";
		boolean aux = true;

		// varre a lista de materias existentes e as atribui na variável auxiliar
		for (Materia materia : this.materias) {// 'this.materias' usuado para referenciar o materias criada na classe e
												// não no
												// método
			mensagem += "\nCódigo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		// pede para o usuário informar o código da matéria desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// recebe o código da matéria desejada e adiciona a materia referente ao código
		// informado
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				materias.add(this.materias.get(codigo - 1));

				// pergunta ao usuário se deseja informar outra matéria
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outra matéria?", "Atenção",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				// processa a opção do usuário, caso a escolha seja sim o loop retorna ao
				// começo, caso a escolha seja não o loop termina
				if (opcao == JOptionPane.YES_OPTION) {
					aux = true;
				} else {
					aux = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroProfessor, 0);
			}
		}

		// retorna a lista de matérias adicionadas
		return materias;
	}

	// Método para retornar o salário de um objeto do tipo Funcionário
	private double getSalario() {
		// variáveis auxiliares
		boolean execute = true;
		double salario = 0;

		// verifica se o salário iinformado é válido
		while (execute) {
			try {
				salario = Double.parseDouble(JOptionPane.showInputDialog(Mensagem.informeSalario));

				// valida o salário informado
				if (Valida.isDoubleZero(salario)) {
					JOptionPane.showMessageDialog(null, Mensagem.salarioVazio, Rotulo.cadastroProfessor, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.salarioInvalido, Rotulo.cadastroProfessor, 0);
				execute = true;
			}
		}

		// retorna o salário informado
		return salario;
	}

	// Método para retornar o código de um objeto do tipo Turma
	private int getCodigoTurma() {
		// variáveis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o código informado é válido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o código informado
				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroTurma, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
				execute = true;
			}
		}

		// retorna o código informado
		return codigo;
	}

	// Método para retornar o ano de um objeto do tipo Turma
	private int getAnoTurma() {
		// variáveis auxiliares
		boolean execute = true;
		int ano = 0;

		// verifica se o ano informado é válido
		while (execute) {
			try {
				ano = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeAno));

				// valida o ano informado
				if (Valida.isIntZero(ano)) {
					JOptionPane.showMessageDialog(null, Mensagem.anoVazio, Rotulo.cadastroTurma, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.anoInvalido, Rotulo.cadastroTurma, 0);
				execute = true;
			}
		}

		// retorna o ano informado
		return ano;
	}

	// Método para retornar um objeto do tipo Professor
	private Professor getProfessorTurma() {

		// declara e instancializa o objeto Professor
		Professor retorno = new Professor();

		// variável auxiliar
		String mensagem = "Professores cadastrados\n";

		// varre a lista de professores existentes e os atribui a variável auxiliar
		for (Professor professor : professores) {
			mensagem += "\nCódigo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		// pede para o usuário informar o código do professor
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o código informado, caso seja válido recebe o professor referente ao
		// código
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = professores.get(codigo - 1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		// retorna o professor desejado
		return retorno;
	}

	// Método para retornar um objeto do tipo Materia
	private Materia getMateriaTurma() {

		// declara e instancializa o objeto Materia
		Materia retorno = new Materia();

		// variável auxiliar
		String mensagem = "Matérias cadastradas\n";

		// varre a lista de materias existentes e as atribui a variável auxiliar
		for (Materia materia : materias) {
			mensagem += "\nCódigo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		// pede para o usuário informar o código da materia desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o código informado, caso seja válido recebe a matéria referente ao
		// código
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = materias.get(codigo - 1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		// retorna a matéria
		return retorno;
	}

	// Método para retornar uma lista de objetos do tipo Aluno
	private ArrayList<Aluno> getAlunosTurma() {

		// declara e instancializa uma lista de objetos Aluno
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		// variáveis auxiliares
		String mensagem = "Alunos cadastrados\n";
		boolean aux = true;

		// varre a lista de alunos existentes e os atribui na variável auxiliar
		for (Aluno aluno : this.alunos) {// 'this.alunos' usuado para referenciar o alunos criado na classe e não no
											// método
			mensagem += "\nCódigo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// pede para o usuário informar o código do aluno desejado para o cadastro
		mensagem += "\n" + Mensagem.informeCodigo;

		// recebe o código do aluno desejado para o cadastro e adiciona o aluno
		// referente ao código informado
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				alunos.add(this.alunos.get(codigo - 1));

				// pergunta ao usuário se deseja informar outro código de aluno
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro aluno?", "Atenção",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				// processa a opção do usuário, caso a escolha seja sim o loop retorna ao
				// começo, caso a escolha seja não o loop termina
				if (opcao == JOptionPane.YES_OPTION) {
					aux = true;
				} else {
					aux = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
			}
		}

		// retorna a lista de alunos
		return alunos;
	}

	// Método para retornar um objeto do tipo Turma
	private Turma getConsultarTurma() {

		// declara e instancializa um objeto Turma
		Turma retorno = new Turma();

		// variável auxiliar
		String mensagem = "Turmas cadastradas\n";

		// varre a lista de turmas existentes e as atribui a variável auxiliar
		for (Turma turma : turmas) {
			mensagem += "\nCódigo: " + turma.getCodigo() + " - " + turma.getAno() + " - "
					+ turma.getMateria().getNome();
		}

		// pede para o usuário informar o código da turma desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o código informado, caso seja válido recebe a turma referente ao
		// código
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = turmas.get(codigo - 1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		// retorna a turma
		return retorno;
	}
}