package ma.sau.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.sau.domain.Categoria;
import ma.sau.repo.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository repo;

	@Override
	public void save(Categoria categoria) {
		repo.save(categoria);
	}

	@Override
	public Optional<Categoria> get(long id) {
		return repo.findById(id);
	}

	@Override
	public Categoria find(String name) {
		return repo.findByNombre(name);
	}

	@Override
	public List<Categoria> getAll() {
		return repo.findAll();
	}

}
