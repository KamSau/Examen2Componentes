package ma.sau.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.sau.domain.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
	public Tarea findByNombre(String word);
}
