package ma.sau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.sau.domain.Categoria;
import ma.sau.service.CategoriaService;

@Controller
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(value = "/categoria/insertar", method = RequestMethod.GET)
	public String insertarCategoria(Model model) {
		model.addAttribute("cat", new Categoria());
		return "categoria/insertar";
	}

	@RequestMapping(value = "/categoria/insertar", method = RequestMethod.POST)
	public String insertarAction(Categoria cat, BindingResult result, Model model) {
		Categoria nueva = cat;
		System.out.println(nueva.toString());
		categoriaService.save(nueva);
		return "redirect:/categoria/listar";
	}

	@GetMapping(value = "/categoria/editar/{id}")
	public String editarPage(@PathVariable("id") long id, Model model) {
		model.addAttribute("cat", categoriaService.get(id));
		return "categoria/editar";
	}

	@RequestMapping(value = "/categoria/editar", method = RequestMethod.POST)
	public String editarAction(Categoria cat, BindingResult result, Model model) {

		Categoria edit = cat;

		System.out.println(cat.toString());

		categoriaService.save(edit);
		return "redirect:/categoria/listar";
	}

	@RequestMapping("/categoria/listar")
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaService.getAll());
		return "categoria/listar";
	}

}
