package ma.sau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.sau.domain.Workshop;
import ma.sau.service.WorkshopService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private WorkshopService workshopService;

	@RequestMapping(path = "/workshopJson", method = RequestMethod.GET)
	public List<Workshop> getAllWorkshops() {

		List<Workshop> ws = workshopService.getAllParaJson();
		System.out.println("Total: " + ws.size());
		return ws;
	}

}
