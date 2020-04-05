package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.Dog;
import dao.DogDAO;

@Service
public class DogViewService {
	
	@Autowired
	private DogDAO dogDAO;
	
	public Dog getDogView(int id) {	
		
		Dog dog = null;
		int updateCount = dogDAO.updateReadCount(id);
		
		if(updateCount > 0){
		dog = dogDAO.selectDog(id);
		}
		
		return dog;
		
	}

}
