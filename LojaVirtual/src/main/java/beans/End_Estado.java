package beans;

import javax.persistence.*;

@Entity
@Table (name="end_estado") 
public class End_Estado {


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="est_id")
	private int id;
	@Column (name="est_nome", length = 30, nullable = true)
	private String nome;
	@Column (name="est_sigla", length = 2, nullable = true)
	private String sigla;

	
	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
