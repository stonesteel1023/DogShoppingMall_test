package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.DogCartListService;
import vo.Cart;

@Controller
public class DogCartListController {

	@Autowired
	private DogCartListService dogCartListService;
	
	@RequestMapping("/dogCartList.dog")
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		ArrayList<Cart> cartList = dogCartListService.getCartList(request);
		//ÃÑ±Ý¾×°è»ê
		int totalMoney = 0;
		int money = 0 ;
		
		for (int i = 0; i < cartList.size(); i++) {
			money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney += money;
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("totalMoney",totalMoney);
		modelAndView.addObject("cartList",cartList);
		modelAndView.setViewName("/dogCartList.jsp");
		return modelAndView;
		
	}

}
