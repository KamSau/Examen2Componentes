package ma.sau.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.sau.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public Categoria findByNombre(String word);
}
