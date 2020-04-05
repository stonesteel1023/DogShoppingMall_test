package controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.DogCartSearchService;
import vo.Cart;

@Controller
public class DogCartSearchController{

	@Autowired
	private DogCartSearchService dogCartSearchService;
	
	@RequestMapping("/dogCartSearch.dog")
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		ArrayList<Cart> cartList = 
		dogCartSearchService.getCartSearchList(startMoney,endMoney,request);
		int totalMoney = 0;
		int money = 0 ;
		
		for (int i = 0; i < cartList.size(); i++) {
			money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney += money;
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cartList", cartList);
		modelAndView.addObject("startMoney", startMoney);
		modelAndView.addObject("endMoney", endMoney);
		modelAndView.addObject("totalMoney", totalMoney);
		modelAndView.setViewName("/dogCartList.jsp");
		return modelAndView;
		
	}
	
}
