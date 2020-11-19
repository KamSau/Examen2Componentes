package ma.sau.service;

import java.util.List;
import java.util.Optional;

import ma.sau.domain.Categoria;
import ma.sau.domain.Workshop;

public interface WorkshopService {

	public void save(Workshop workshop);

	public Optional<Workshop> get(Long id);

	public List<Workshop> getAll();

	public List<Workshop> getAllParaJson();

	public List<Workshop> findByNombreContaining(String name);

	public List<Workshop> findByAutor(String name);

	public List<Workshop> findByKeyword(String keyword);

	public List<Workshop> findByCategoria(Categoria cat);
}
