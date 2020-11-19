package ma.sau.service;

import java.util.List;
import java.util.Optional;

import ma.sau.domain.Categoria;

public interface CategoriaService {

	public void save(Categoria categoria);

	public Optional<Categoria> get(long id);

	public List<Categoria> getAll();

	public Categoria find(String name);
}
