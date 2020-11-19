package ma.sau.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.sau.domain.Categoria;
import ma.sau.domain.Workshop;
import ma.sau.domain.sort.SortTareas;
import ma.sau.repo.WorkshopRepository;

@Service
public class WorkshopServiceImpl implements WorkshopService {

	@Autowired
	WorkshopRepository repo;

	@Override
	public void save(Workshop workshop) {
		repo.save(workshop);
	}

	@Override
	public Optional<Workshop> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Workshop> findByNombreContaining(String name) {
		return repo.findByNombreContaining(name);
	}

	@Override
	public List<Workshop> getAll() {
		List<Workshop> ws = repo.findAll();
		ws.forEach((workshop -> {
			Collections.sort(workshop.getTareas(), new SortTareas());
			workshop.calcularTiempo();
		}));
		return ws;
	}

	@Override
	public List<Workshop> findByAutor(String name) {
		List<Workshop> ws = repo.findByAutor(name);
		ws.forEach((workshop -> {
			Collections.sort(workshop.getTareas(), new SortTareas());
			workshop.calcularTiempo();
		}));
		return ws;
	}

	@Override
	public List<Workshop> findByKeyword(String keyword) {
		List<Workshop> retornar = repo.findAll();
		retornar.removeIf(p -> (!p.getKeywords().contains(keyword)));
		retornar.forEach((workshop -> {
			Collections.sort(workshop.getTareas(), new SortTareas());
			workshop.calcularTiempo();
		}));
		return retornar;
	}

	@Override
	public List<Workshop> findByCategoria(Categoria cat) {
		List<Workshop> ws = repo.findByCategoria(cat);
		ws.forEach((workshop -> {
			Collections.sort(workshop.getTareas(), new SortTareas());
			workshop.calcularTiempo();
		}));
		return ws;
	}

	@Override
	public List<Workshop> getAllParaJson() {
		List<Workshop> retornar = repo.findAll();
		retornar.forEach((workshop -> {
			workshop.calcularTiempo();
			workshop.setTareas(null);
			workshop.setIdCategoria((int) workshop.getCategoria().getId());
			workshop.setCategoria(null);
		}));
		return retornar;
	}
}
