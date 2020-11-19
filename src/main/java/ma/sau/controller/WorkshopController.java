package ma.sau.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.sau.domain.Categoria;
import ma.sau.domain.Workshop;
import ma.sau.service.CategoriaService;
import ma.sau.service.WorkshopService;

@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;
	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(value = "/workshop/insertar", method = RequestMethod.GET)
	public String insertarWorkshop(Model model) {
		model.addAttribute("categorias", categoriaService.getAll());
		model.addAttribute("ws", new Workshop());
		System.out.println(new Workshop().toString());
		return "workshop/insertar";
	}

	@RequestMapping(value = "/workshop/insertar", method = RequestMethod.POST)
	public String insertarAction(Workshop ws, BindingResult result, Model model) {
		Workshop nueva = ws;
		Categoria cat = categoriaService.get(ws.getIdCategoria()).get();
		if (cat != null) {
			System.out.println(cat.toString());
		}
		ws.setCategoria(cat);
		System.out.println(nueva.toString());

		if (nueva.getKeywords() != null && nueva.getKeywords().size() > 3) {
			List<String> s = nueva.getKeywords();
			s.removeIf((f) -> s.lastIndexOf(f) > 2);
			nueva.setKeywords(s);
		}

		workshopService.save(nueva);
		return "redirect:/workshop/listar";
	}

	@GetMapping(value = "/workshop/editar/{id}")
	public String editarPage(@PathVariable("id") long id, Model model) {
		Workshop ws = workshopService.get(id).get();
		ws.setIdCategoria((int) ws.getCategoria().getId());
		model.addAttribute("categorias", categoriaService.getAll());
		model.addAttribute("ws", ws);
		return "workshop/editar";
	}

	@RequestMapping(value = "/workshop/editar", method = RequestMethod.POST)
	public String editarAction(Workshop ws, BindingResult result, Model model) {

		Workshop edit = ws;
		System.out.println(edit.toString());
		Categoria cat = categoriaService.get(edit.getIdCategoria()).get();
		if (cat != null) {
			System.out.println(cat.toString());
		}
		edit.setCategoria(cat);
		System.out.println(ws.toString());

		if (edit.getKeywords() != null && edit.getKeywords().size() > 3) {
			List<String> s = edit.getKeywords();
			s.removeIf((f) -> s.lastIndexOf(f) > 2);
			edit.setKeywords(s);
		}
		workshopService.save(edit);
		return "redirect:/workshop/listar";
	}

	@RequestMapping("/workshop/listar")
	public String listar(Model model) {
		model.addAttribute("ws", workshopService.getAll());
		return "workshop/listar";
	}

	@GetMapping(value = "/workshop/buscarAutor")
	public String buscarAutor() {
		return "workshop/buscarAutor";
	}

	@GetMapping(value = "/workshop/listarAutor/{nombre}")
	public String listarAutor(@PathVariable("nombre") String nombre, Model model) {
		model.addAttribute("ws", workshopService.findByAutor(nombre));
		return "workshop/listar";
	}

	@GetMapping(value = "/workshop/buscarKeyword")
	public String buscarKeyword() {
		return "workshop/buscarKeyword";
	}

	@GetMapping(value = "/workshop/listarKeyword")
	public String listarKeyword(@RequestParam(name = "keyword") String keyword, Model model) {
		model.addAttribute("ws", workshopService.findByKeyword(keyword));
		return "workshop/listar";
	}

	@RequestMapping("/workshop/buscarCategoria")
	public String listarCategoria(Model model) {
		model.addAttribute("categorias", categoriaService.getAll());
		return "workshop/buscarCategoria";
	}

	@GetMapping(value = "/workshop/listarCategoria/{categoriaId}")
	public String listarKeyword(@PathVariable("categoriaId") long categoriaId, Model model) {
		Categoria cat = categoriaService.get(categoriaId).get();

		model.addAttribute("ws", workshopService.findByCategoria(cat));
		return "workshop/listar";
	}

	@GetMapping(value = "/workshop/listarDatatables")
	public String listarDatatables() {
		return "workshop/listarDatatables";
	}

	@GetMapping(value = "/workshop/reporte/{id}")
	public void getGeneratedDocument(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		Workshop ws = workshopService.get(id).get();
		ws.calcularTiempo();
		response.setHeader("Content-disposition", "attachment; filename=" + ws.getNombre() + ".docx");
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText(ws.getNombre() + " por: " + ws.getAutor());
		titleRun.setColor("009933");
		titleRun.setBold(true);
		titleRun.setFontFamily("Courier");
		titleRun.setFontSize(20);

		XWPFParagraph subTitle = document.createParagraph();
		subTitle.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun subTitleRun = subTitle.createRun();
		subTitleRun.setColor("00CC44");
		subTitleRun.setFontFamily("Courier");
		subTitleRun.setFontSize(16);
		subTitleRun.setTextPosition(20);
		subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
		subTitleRun.setText("Categoría: " + ws.getCategoria().getNombre() + "\n ");
		subTitleRun.addCarriageReturn();
		subTitleRun.setText("Tiempo estimado en minutos: " + ws.getTiempo() + "\n ");
		subTitleRun.addCarriageReturn();
		subTitleRun.setText("Objetivo: " + ws.getObjetivo() + "\n ");
		subTitleRun.addCarriageReturn();
		subTitleRun.setText("Keywords: " + ws.getKeywords() + "\n ");

		XWPFParagraph tareasTitulo = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun tareasTituloRun = tareasTitulo.createRun();
		tareasTituloRun.setText("Tareas");
		tareasTituloRun.setColor("000000");
		tareasTituloRun.setBold(true);
		tareasTituloRun.setFontFamily("Courier");
		tareasTituloRun.setFontSize(15);

		XWPFParagraph tareas = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun tareasRun = tareas.createRun();
		tareasRun.setColor("000000");
		tareasRun.setFontFamily("Courier");
		tareasRun.setFontSize(14);

		if (ws.getTareas() != null && ws.getTareas().size() > 0) {
			ws.getTareas().forEach((tarea) -> {
				tareasRun.setText("#" + tarea.getNombre() + "#" + "\n ");
				tareasRun.addBreak();
				tareasRun.setText("Descripción: " + tarea.getDescripcion() + "\n ");
				tareasTituloRun.setBold(false);
				tareasRun.addBreak();
				tareasRun.setText("Texto: " + tarea.getTexto() + "\n ");
				tareasRun.addBreak();
				tareasRun.setText("Tiempo: " + tarea.getTiempo() + " minutos \n ");
				tareasRun.addBreak();
				tareasRun.addBreak();
			});
		} else {
			tareasRun.setBold(true);
			tareasRun.setText("No hay tareas");
		}

		document.write(response.getOutputStream());
		document.close();
	}

}