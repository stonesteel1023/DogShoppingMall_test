package controller;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.DogListService;
import vo.Dog;

@Controller
public class DogListController {
	
	@Autowired
	private DogListService dogListService;
	
	@RequestMapping("/dogList.dog")
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		ArrayList<String> todayImageList = new ArrayList<String>();
		Cookie[] cookieArray = request.getCookies();
		
		if(cookieArray != null){
			for (int i = 0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().startsWith("today")){
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		
		ArrayList<Dog> dogList = dogListService.getDogList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("dogList", dogList);
		modelAndView.addObject("todayImageList", todayImageList);
		modelAndView.setViewName("/dogList.jsp");
		return modelAndView;
		
	}
	
}
