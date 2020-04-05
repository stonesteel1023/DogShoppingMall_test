package svc;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.DogDAO;
import vo.Dog;

@Service
public class DogListService {
	
	@Autowired
	private DogDAO dogDAO;
	
	public ArrayList<Dog> getDogList() {
		
		return dogDAO.selectDogList();
		
	}
	
}