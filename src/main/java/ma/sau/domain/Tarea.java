package ma.sau.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "texto")
	private String texto;

	@Column(name = "tiempo")
	private float tiempo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workshop_id")
	private Workshop workshop;

	public Tarea() {
	}

	public Tarea(long id) {
		this.id = id;
	}

	public Tarea(long id, String nombre, String descripcion, String texto, float tiempo, Workshop workshop) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.texto = texto;
		this.tiempo = tiempo;
		this.workshop = workshop;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public float getTiempo() {
		return tiempo;
	}

	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", texto=" + texto
				+ ", tiempo=" + tiempo + ", workshop=" + workshop + "]";
	}

}
