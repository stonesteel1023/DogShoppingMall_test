package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import svc.DogCartRemoveService;

@Controller
public class DogCartRemoveController {
	
	@Autowired
	private DogCartRemoveService dogCartRemoveService;
	
	@RequestMapping("/dogCartRemove.dog")
	public String execute(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String[] kindArray = request.getParameterValues("remove");
		dogCartRemoveService.cartRemove(request,kindArray);
		return "dogCartList.dog";
		
	}
	
}