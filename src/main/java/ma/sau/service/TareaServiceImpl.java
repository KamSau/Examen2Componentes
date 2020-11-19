package ma.sau.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.sau.domain.Tarea;
import ma.sau.repo.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	TareaRepository repo;

	@Override
	public void save(Tarea tarea) {
		repo.save(tarea);
	}

	@Override
	public Optional<Tarea> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public Tarea find(String name) {
		return repo.findByNombre(name);
	}

	@Override
	public List<Tarea> getAll() {
		return repo.findAll();
	}
}
