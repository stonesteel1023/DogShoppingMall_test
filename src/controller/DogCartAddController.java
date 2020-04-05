package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.DogCartAddService;
import vo.Dog;

@Controller
public class DogCartAddController {
	
	@Autowired
	private DogCartAddService dogCartAddService;
	
	@RequestMapping("/dogCartAdd.dog")
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Dog cartDog = dogCartAddService.getCartDog(id);
		dogCartAddService.addCart(request,cartDog);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/dogCartList.dog");
		return modelAndView;
		
	}

}
