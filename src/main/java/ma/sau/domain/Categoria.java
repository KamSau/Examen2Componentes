package ma.sau.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "categoria")
	private Set<Workshop> workshops;

	public Categoria() {
	}

	public Categoria(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Workshop> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(Set<Workshop> workshops) {
		this.workshops = workshops;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}

}
