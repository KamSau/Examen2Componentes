package ma.sau.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.sau.domain.Categoria;
import ma.sau.domain.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
	public List<Workshop> findByNombreContaining(String word);

	public List<Workshop> findByAutor(String name);

	public List<Workshop> findByCategoria(Categoria cat);
}
