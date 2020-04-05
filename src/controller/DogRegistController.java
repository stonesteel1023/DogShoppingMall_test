package controller;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import svc.DogRegistService;
import vo.Dog;

@Controller
public class DogRegistController{

	@Autowired
	private DogRegistService dogRegistService;
	
	@RequestMapping("/dogRegist.dog")
	public ModelAndView execute(Dog dog,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String realFolder = "";
		//파일 업로드될 서버 상의 물리적인 경로
		String saveFolder = "/images";
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartFile image = dog.getImageFile();
		
		String filename = image.getOriginalFilename();
		File uploadFile = new File(realFolder + "/" + filename);
		try{
			image.transferTo(uploadFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		dog.setImage(filename);
		boolean isRegistSuccess = dogRegistService.registDog(dog);
		
		ModelAndView modelAndView = null;
		if(isRegistSuccess){
			modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/dogList.dog");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return modelAndView;
		
	}

}
