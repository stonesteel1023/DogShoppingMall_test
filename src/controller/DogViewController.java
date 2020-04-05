package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.DogViewService;
import vo.Dog;

@Controller
public class DogViewController {
	
	@Autowired
	private DogViewService dogViewService;
	
	@RequestMapping("/dogView.dog")
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Dog dog = dogViewService.getDogView(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/dogView.jsp");
		modelAndView.addObject("dog", dog);
		Cookie todayImageCookie = new Cookie("today"+id, dog.getImage());
		todayImageCookie.setMaxAge(60*60*24);
		response.addCookie(todayImageCookie);
		return modelAndView;
		
	}
	
}