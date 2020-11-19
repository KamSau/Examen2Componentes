package ma.sau.service;

import java.util.List;
import java.util.Optional;

import ma.sau.domain.Tarea;

public interface TareaService {

	public void save(Tarea tarea);

	public Optional<Tarea> get(Long id);

	public List<Tarea> getAll();

	public Tarea find(String name);
}
