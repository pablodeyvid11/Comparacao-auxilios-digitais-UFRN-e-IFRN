package application;

public class Alune {
	private String nome;
	private char inicial;
	
	public Alune(String nome, char inicial) {
		this.nome = nome;
		this.inicial = inicial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getInicial() {
		return inicial;
	}

	public void setInicial(char inicial) {
		this.inicial = inicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inicial;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alune other = (Alune) obj;
		if (inicial != other.inicial)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getNome());
		return builder.toString();
	}
	
	
}
