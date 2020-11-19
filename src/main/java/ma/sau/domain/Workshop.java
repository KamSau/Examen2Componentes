package ma.sau.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ma.sau.domain.converter.ConvertidorKeywords;

@Entity
public class Workshop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "autor")
	private String autor;

	@Column(name = "objetivo")
	private String objetivo;

	@ManyToOne
	@JoinColumn(name = "workshops", nullable = false)
	private Categoria categoria;

	@Column(name = "keywords")
	@Convert(converter = ConvertidorKeywords.class)
	private List<String> keywords;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
	private List<Tarea> tareas;

	@Transient
	private int idCategoria;

	@Transient
	private float tiempo;

	public Workshop() {
	}

	public Workshop(long id) {
		this.id = id;
	}

	public Workshop(long id, String nombre, String autor, String objetivo, Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.objetivo = objetivo;
		this.categoria = categoria;
	}

	public Workshop(long id, String nombre, String autor, String objetivo, Categoria categoria, List<String> keywords) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.objetivo = objetivo;
		this.categoria = categoria;
		this.keywords = keywords;
	}

	public Workshop(long id, String nombre, String autor, String objetivo, Categoria categoria, List<String> keywords,
			List<Tarea> tareas) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.objetivo = objetivo;
		this.categoria = categoria;
		this.keywords = keywords;
		this.tareas = tareas;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void calcularTiempo() {
		this.tiempo = 0;
		if (this.tareas != null && this.tareas.size() > 0) {
			this.getTareas().forEach((tarea) -> {
				this.tiempo += tarea.getTiempo();
			});
		}
	}

	public float getTiempo() {
		return tiempo;
	}

	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Workshop [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", objetivo=" + objetivo
				+ ", categoria=" + categoria + ", keywords=" + keywords + ", tareas=" + tareas + ", idCategoria="
				+ idCategoria + "]";
	}

}
