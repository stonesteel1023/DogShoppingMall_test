package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.DogDAO;
import vo.Dog;

@Service
public class DogRegistService {
	
	@Autowired
	private DogDAO dogDAO;
	
	public boolean registDog(Dog dog) {
		
		boolean isRegistSuccess = false;		
		int insertCount = dogDAO.insertDog(dog);
		
		if(insertCount>0){
			isRegistSuccess=true;
		}
		
		return isRegistSuccess;
		
	}

}
