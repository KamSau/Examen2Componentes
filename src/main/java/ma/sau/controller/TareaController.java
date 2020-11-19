package ma.sau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.sau.domain.Tarea;
import ma.sau.domain.Workshop;
import ma.sau.service.TareaService;
import ma.sau.service.WorkshopService;

@Controller
public class TareaController {

	@Autowired
	WorkshopService workshopService;
	@Autowired
	TareaService tareaService;

	@RequestMapping(value = "/tarea/insertar/{id}", method = RequestMethod.GET)
	public String insertarTarea(@PathVariable("id") long id, Model model) {
		model.addAttribute("t", new Tarea());
		model.addAttribute("id", id);
		return "tarea/insertar";
	}

	@RequestMapping(value = "/tarea/insertar/{id}", method = RequestMethod.POST)
	public String insertarAction(@PathVariable("id") long id, Tarea t, BindingResult result, Model model) {
		Tarea nueva = t;
		Workshop ws = workshopService.get(id).get();
		nueva.setWorkshop(ws);
		tareaService.save(nueva);
		return "redirect:/workshop/listar";
	}

}