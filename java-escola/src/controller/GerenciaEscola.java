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
 * Classe respons�vel por controlar o sistema de gerenciamento para cadastro de
 * aluno, turmas, mat�rias e professores.
 * 
 * @author Victor Baptista
 *
 */
public class GerenciaEscola {

	// declarando as listas para armazenar os conte�dos do sistema( ALUNO E
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

	// M�todo para criar um menu de op��es
	public void menuPrincipal() {

		// vari�vel auxiliar de mensagem
		String menu = "INFORME A OP��O DESEJADA\n\n" + "1-CADASTRAR MAT�RIA\n" + "2-CADASTRAR PROFESSOR\n"
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

	// Processa o menu e as escolhas do usu�rio
	public void processamentoPrincipal(int opcao) {

		// acessa o setor do menu baseado na escolha do usu�rio
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

	// m�todo para cadastrar mat�ria
	public void cadastrarMateria() {

		// Adiciona uma mat�ria na lista de dispon�veis
		materias.add(getMateria());

		// informa ao usu�rio sucesso ao cadastrar as mat�rias
		JOptionPane.showMessageDialog(null, Mensagem.materiaCadastrada, Rotulo.cadastroMateria, 1);
	}

	// m�todo para cadastrar professor
	public void cadastrarProfessor() {

		// adiciona um professor a lista de dispon�veis e informa o usu�rio se o
		// cadastro ocorreu com sucesso ou n�o
		if (materias.size() > 0) {
			professores.add(getProfessor());
			JOptionPane.showMessageDialog(null, Mensagem.professorCadastrado, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroProfessor, Rotulo.cadastroProfessor, 2);
		}
	}

	// m�todo para cadastrar aluno
	public void cadastrarAluno() {

		// adiciona um aluno
		alunos.add(getAluno());

		// informa o usu�rio sucesso ao cadastrar o aluno
		JOptionPane.showMessageDialog(null, Mensagem.alunoCadastrado, Rotulo.cadastroAluno, 1);
	}

	// m�todo para cadastrar turma
	public void cadastrarTurma() {

		// adiciona uma turma e informa ao usu�rio sucesso ou fracasso do cadastro da
		// turma
		if ((professores.size() > 0) && (alunos.size() > 0)) {
			turmas.add(getTurma());
			JOptionPane.showMessageDialog(null, Mensagem.turmaCadastrada, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroTurma, Rotulo.cadastroTurma, 2);
		}
	}

	// M�todo para retornar um objeto do tipo turma
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

	// m�todo para listar alunos cadastrados
	public void listarAlunos() {

		// vari�veis auxiliares
		String mensagem = "Alunos cadastrados\n";
		boolean existe = false;

		// varre a lista de alunos existente e caso existir os atribui na vari�vel
		// auxiliar
		for (Aluno aluno : alunos) {
			existe = true;
			mensagem += "\nC�digo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// exibe a mensagem auxiliar contendo os alunos exstentes ou exibe que n�o h�
		// alunos cadastrados
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroAluno, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.alunosVazio, Rotulo.cadastroAluno, 2);
		}
	}

	// m�todo para listar alunos cadastrados
	public void listarProfessores() {

		// vari�veis auxiliares
		String mensagem = "Professores cadastrados\n";
		boolean existe = false;

		// varre a lista de professores existente e caso existir os atribui na vari�vel
		// auxiliar
		for (Professor professor : professores) {
			existe = true;
			mensagem += "\nC�digo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		// exibe a mensagem auxiliar contendo os professores existentes ou exibe que n�o
		// h� professores cadastrados
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.professoresVazio, Rotulo.cadastroProfessor, 2);
		}
	}

	// m�todo para listar alunos cadastrados
	public void listarTurmas() {

		// vari�veis auxiliares
		String mensagem = "Turmas cadastradas\n";
		boolean existe = false;

		// varre a lista de turmas existente e caso existir as atribui na vari�vel
		// auxiliar
		for (Turma turma : turmas) {
			existe = true;
			mensagem += "\nC�digo: " + turma.getCodigo() + " - " + turma.getAno() + " - "
					+ turma.getMateria().getNome();
		}

		// exibe a mensagem auxiliar contendo as turmas existentes ou exibe que n�o h�
		// turmas cadastradas
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.turmasVazio, Rotulo.cadastroTurma, 2);
		}
	}

	// m�todo para consultar uma turma espec�fica
	public void consultarTurma() {

		// declara e instancializa um objeto Turma
		Turma turma = getConsultarTurma();

		// declara vari�vel auxiliar e atribui para ela os atributos do objeto Turma
		String mensagem = "Detalhes da Turma\n";
		mensagem += "\nC�digo: " + turma.getCodigo();
		mensagem += "\nAno: " + turma.getAno();
		mensagem += "\nProfessor: " + turma.getProfessor().getNome();
		mensagem += "\nMateria: " + turma.getMateria().getNome();
		mensagem += "\n\nAlunos Matriculados:";

		// varre o vetor de alunos do objeto turma e os atribui na vari�vel auxiliar
		for (Aluno aluno : turma.getAlunos()) {
			mensagem += "\nC�digo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// exibe a vari�vel auxliar com todos os dados do objeto Turma
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.consultaTurma, 1);

	}

	// m�todo para sair do sistema
	public void sairSistema() {

		// Mensagem de confirma��o para sair do programa
		int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sistema?", "Aten��o", JOptionPane.YES_OPTION,
				JOptionPane.CANCEL_OPTION);

		// caso o usu�rio escolha a op��o sim, o programa finaliza
		if (opcao == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// m�todo para retornar os atributos do aluno
	public Aluno getAluno() {
		// declara e instancializa objetos do tipo Aluno, Endere�o e Contato
		Aluno aluno = new Aluno();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		// atribui valores ao objeto Aluno
		aluno.setCodidgo(getCodigoAluno());
		aluno.setNome(getNomeAluno());
		aluno.setCpf(getCpfAluno());
		aluno.setRg(getRgAluno());

		// atribui valores ao objeto Endere�o
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

		// atribui valores do objeto Endere�o e Contato ao objeto Aluno
		aluno.setEndereco(endereco);
		aluno.setContato(contato);

		// retorna o objeto Aluno
		return aluno;
	}

	// m�todo para reatornar os atributos do professor
	private Professor getProfessor() {
		// declara e instancializa objetos do tipo Professor, Endere�o e Contato
		Professor professor = new Professor();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		// atribui valores ao objeto Professor
		professor.setCodigo(getCodigoProfessor());
		professor.setNome(getNomeProfessor());
		professor.setCpf(getCpfProfessor());
		professor.setRg(getRgProfessor());
		professor.setSalario(getSalario());

		// atribui valores ao objeto Endere�o
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

		// Atribui valores do objeto Endere�o e Contato ao objeto Professor
		professor.setEndereco(endereco);
		professor.setContato(contato);

		// Atribui valores do tipo Materia ao objeto Professor
		professor.setListaMaterias(getMaterias());

		// retorna o objeto professor
		return professor;
	}

	// M�todo para retornar um objeto materia
	private Materia getMateria() {

		// declara e instancializa objeto Materia
		Materia materia = new Materia();

		// atribui valores ao objeto Materia
		materia.setCodigo(getCodigoMateria());
		materia.setNome(getNomeMateria());

		// retorna o objeto materia
		return materia;
	}

	// M�todo para retornar o c�digo de um objeto do tipo Materia
	private int getCodigoMateria() {
		// vari�veis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o c�digo informado � v�lido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o c�digo informado
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

		// retorna o c�digo do objeto Materia
		return codigo;
	}

	// M�todo para retornar o nome de um objeto do tipo Materia
	private String getNomeMateria() {

		// vari�veis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado � v�lido
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

	// M�todo para retornar o c�digo de um objeto do tipo Aluno
	private int getCodigoAluno() {
		// vari�veis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o c�digo informado � v�lido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o c�digo informado
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

		// retorna o c�digo informado
		return codigo;
	}

	// M�todo para retornar o nome de um objeto do tipo Aluno
	private String getNomeAluno() {
		// vari�veis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado � valido
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

	// M�todo para retornar o CPF de um objeto do tipo Aluno
	private String getCpfAluno() {
		// vari�veis auxiliares
		boolean execute = true;
		String cpf = "";

		// verifica se o cpf informado � v�lido
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

	// M�todo para retornar o RG de um objeto do tipo Aluno
	private String getRgAluno() {
		// vari�veis auxiliares
		boolean execute = true;
		String rg = "";

		// verifica se o rg informado � v�lido
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

	// M�todo para retornar o logradouro de um objeto do tipo Endere�o
	private String getLogradouro() {
		// vari�veis auxiliares
		boolean execute = true;
		String logradouro = "";

		// verifica se o logradouro informado � valido
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

	// M�todo para retornar o nome do logradouro de um objeto do tipo Endere�o
	private String getNomeLogradouro() {
		// vari�veis auxiliares
		boolean execute = true;
		String nomeLogradouro = "";

		// verifica se o nome do logradouro � v�lido
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

	// M�todo para retornar o n�mero de um objeto do tipo Endere�o
	private int getNumero() {
		// vari�veis auxiliares
		boolean execute = true;
		int numero = 0;

		// verifica se o n�mero informado � v�lido
		while (execute) {
			try {
				numero = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeNumero));

				// valida o n�emro informado
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

		// retorna o n�mero informado
		return numero;
	}

	// M�todo para retornar o complemento de um objeto do tipo Endere�o
	private String getComplemento() {
		// vari�vel auxiliar
		String complemento = "";

		// recebe o complemento do usu�rio
		complemento = JOptionPane.showInputDialog(Mensagem.informeComplemento);

		// retorna o complemento informado
		return complemento;
	}

	// M�todo para retornar o bairro de um objeto do tipo Endere�o
	private String getBairro() {
		// vari�veis auxiliares
		boolean execute = true;
		String bairro = "";

		// verifica se o bairro informado � v�lido
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

	// M�todo para retornar a cidade de um objeto do tipo Endere�o
	private String getCidade() {
		// vari�veis auxiliares
		boolean execute = true;
		String cidade = "";

		// verifica se a cidade informada � v�lida
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

	// M�todo para retornar o estado de um objeto do tipo Endere�o
	private String getEstado() {
		// vari�veis auxiliares
		boolean execute = true;
		String estado = "";

		// verifica se o estado informado � v�lido
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

	// M�todo para retornar o CEP de um objeto do tipo Endere�o
	private String getCep() {
		// vari�veis auxiliares
		boolean execute = true;
		String cep = "";

		// verifica se o CEP informado � v�lido
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

	// M�todo para retornar o celurar de um objeto do tipo Contato
	private String getCelular() {
		// vari�veis auxiliares
		boolean execute = true;
		String celular = "";

		// verifica se o celular informado � v�lido
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

	// M�todo para retornar o email de um objeto do tipo Contato
	private String getEmail() {
		// vari�veis auxiliares
		boolean execute = true;
		String email = "";

		// verifica se o email informado � v�lido
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

	// M�todo para retornar o c�digo de um objeto do tipo Professor
	private int getCodigoProfessor() {
		// vari�veis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o c�digo informado � v�lido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o c�digo informado
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

		// retorna o c�digo informado
		return codigo;
	}

	// M�todo para retornar o nome de um objeto do tipo Professor
	private String getNomeProfessor() {
		// vari�veis auxiliares
		boolean execute = true;
		String nome = "";

		// verifica se o nome informado � v�lido
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

	// M�todo para retornar o CPF de um objeto do tipo Professor
	private String getCpfProfessor() {
		// vari�veis auxiliares
		boolean execute = true;
		String cpf = "";

		// verifica se o CPF informado � v�lido
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

	// M�todo para retornar o PG de um objeto do tipo Professor
	private String getRgProfessor() {
		// vari�veis auxiliares
		boolean execute = true;
		String rg = "";

		// verifica se o RG informado � v�lido
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

	// M�todo para retornar a lista de materias de um objeto do tipo Materia
	private ArrayList<Materia> getMaterias() {

		// declara e instancializa uma lista de objeto do tipo Materia
		ArrayList<Materia> materias = new ArrayList<Materia>();

		// vari�veis auxiliares
		String mensagem = "Mat�rias cadastradas\n";
		boolean aux = true;

		// varre a lista de materias existentes e as atribui na vari�vel auxiliar
		for (Materia materia : this.materias) {// 'this.materias' usuado para referenciar o materias criada na classe e
												// n�o no
												// m�todo
			mensagem += "\nC�digo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		// pede para o usu�rio informar o c�digo da mat�ria desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// recebe o c�digo da mat�ria desejada e adiciona a materia referente ao c�digo
		// informado
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				materias.add(this.materias.get(codigo - 1));

				// pergunta ao usu�rio se deseja informar outra mat�ria
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outra mat�ria?", "Aten��o",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				// processa a op��o do usu�rio, caso a escolha seja sim o loop retorna ao
				// come�o, caso a escolha seja n�o o loop termina
				if (opcao == JOptionPane.YES_OPTION) {
					aux = true;
				} else {
					aux = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroProfessor, 0);
			}
		}

		// retorna a lista de mat�rias adicionadas
		return materias;
	}

	// M�todo para retornar o sal�rio de um objeto do tipo Funcion�rio
	private double getSalario() {
		// vari�veis auxiliares
		boolean execute = true;
		double salario = 0;

		// verifica se o sal�rio iinformado � v�lido
		while (execute) {
			try {
				salario = Double.parseDouble(JOptionPane.showInputDialog(Mensagem.informeSalario));

				// valida o sal�rio informado
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

		// retorna o sal�rio informado
		return salario;
	}

	// M�todo para retornar o c�digo de um objeto do tipo Turma
	private int getCodigoTurma() {
		// vari�veis auxiliares
		boolean execute = true;
		int codigo = 0;

		// verifica se o c�digo informado � v�lido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				// valida o c�digo informado
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

		// retorna o c�digo informado
		return codigo;
	}

	// M�todo para retornar o ano de um objeto do tipo Turma
	private int getAnoTurma() {
		// vari�veis auxiliares
		boolean execute = true;
		int ano = 0;

		// verifica se o ano informado � v�lido
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

	// M�todo para retornar um objeto do tipo Professor
	private Professor getProfessorTurma() {

		// declara e instancializa o objeto Professor
		Professor retorno = new Professor();

		// vari�vel auxiliar
		String mensagem = "Professores cadastrados\n";

		// varre a lista de professores existentes e os atribui a vari�vel auxiliar
		for (Professor professor : professores) {
			mensagem += "\nC�digo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		// pede para o usu�rio informar o c�digo do professor
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o c�digo informado, caso seja v�lido recebe o professor referente ao
		// c�digo
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = professores.get(codigo - 1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		// retorna o professor desejado
		return retorno;
	}

	// M�todo para retornar um objeto do tipo Materia
	private Materia getMateriaTurma() {

		// declara e instancializa o objeto Materia
		Materia retorno = new Materia();

		// vari�vel auxiliar
		String mensagem = "Mat�rias cadastradas\n";

		// varre a lista de materias existentes e as atribui a vari�vel auxiliar
		for (Materia materia : materias) {
			mensagem += "\nC�digo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		// pede para o usu�rio informar o c�digo da materia desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o c�digo informado, caso seja v�lido recebe a mat�ria referente ao
		// c�digo
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = materias.get(codigo - 1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		// retorna a mat�ria
		return retorno;
	}

	// M�todo para retornar uma lista de objetos do tipo Aluno
	private ArrayList<Aluno> getAlunosTurma() {

		// declara e instancializa uma lista de objetos Aluno
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		// vari�veis auxiliares
		String mensagem = "Alunos cadastrados\n";
		boolean aux = true;

		// varre a lista de alunos existentes e os atribui na vari�vel auxiliar
		for (Aluno aluno : this.alunos) {// 'this.alunos' usuado para referenciar o alunos criado na classe e n�o no
											// m�todo
			mensagem += "\nC�digo: " + aluno.getCodidgo() + " - " + aluno.getNome();
		}

		// pede para o usu�rio informar o c�digo do aluno desejado para o cadastro
		mensagem += "\n" + Mensagem.informeCodigo;

		// recebe o c�digo do aluno desejado para o cadastro e adiciona o aluno
		// referente ao c�digo informado
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				alunos.add(this.alunos.get(codigo - 1));

				// pergunta ao usu�rio se deseja informar outro c�digo de aluno
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro aluno?", "Aten��o",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				// processa a op��o do usu�rio, caso a escolha seja sim o loop retorna ao
				// come�o, caso a escolha seja n�o o loop termina
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

	// M�todo para retornar um objeto do tipo Turma
	private Turma getConsultarTurma() {

		// declara e instancializa um objeto Turma
		Turma retorno = new Turma();

		// vari�vel auxiliar
		String mensagem = "Turmas cadastradas\n";

		// varre a lista de turmas existentes e as atribui a vari�vel auxiliar
		for (Turma turma : turmas) {
			mensagem += "\nC�digo: " + turma.getCodigo() + " - " + turma.getAno() + " - "
					+ turma.getMateria().getNome();
		}

		// pede para o usu�rio informar o c�digo da turma desejada
		mensagem += "\n" + Mensagem.informeCodigo;

		// verifica o c�digo informado, caso seja v�lido recebe a turma referente ao
		// c�digo
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