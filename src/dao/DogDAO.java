package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import vo.Dog;

@Repository
public class DogDAO extends JdbcDaoSupport{

	@Autowired
	public DogDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		setDataSource(dataSource);
	}


//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//		// 1. JDBC ����̺� �ε�...
//		Class.forName("oracle.jdbc.OracleDriver");
//		// 2. �������� Connection ��ü����...
//		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:oracle", "C##STONESTEEL", "tiger");
//		// 3.Statement ��ü ����...
//		Statement stmt = conn.createStatement();
//		// 4. ���� ����...
//		stmt.executeUpdate("insert into dog values (dog_seq.nextval,'Ǫ��2',1000,'pu.jpg','������',1,20,'�и���',0)");
//		// 5. ���ҽ� ��ȯ
//		stmt.close();
//		conn.close();
//	}

	public ArrayList<Dog> selectDogList() {

		String sql = "SELECT * FROM dog";

		RowMapper<Dog> rowMapper = new RowMapper<Dog>(){
			//���ڵ� �����͵��� Ư���� ��ü�� �������ִ� ����� ��.
			@Override
			public Dog mapRow(ResultSet rs, int arg1) throws SQLException {
				Dog dog = new Dog(
						rs.getInt("id")
						,rs.getString("kind")
						,rs.getInt("price")
						,rs.getString("image")
						,rs.getString("country")
						,rs.getInt("height")
						,rs.getInt("weight")
						,rs.getString("content")
						,rs.getInt("readcount"));

				return dog;
			}
		};


		return (ArrayList<Dog>)getJdbcTemplate().query(sql, rowMapper);

	}

	public Dog selectDog(int id) {

		String sql = "SELECT * FROM dog WHERE id=?";
		Object[] idValue = new Object[]{
				id	
		};	

		RowMapper<Dog> rowMapper = new RowMapper<Dog>(){
			//���ڵ� �����͵��� Ư���� ��ü�� �������ִ� ����� ��.
			@Override
			public Dog mapRow(ResultSet rs, int arg1) throws SQLException {
				Dog dog = new Dog(
						rs.getInt("id")
						,rs.getString("kind")
						,rs.getInt("price")
						,rs.getString("image")
						,rs.getString("country")
						,rs.getInt("height")
						,rs.getInt("weight")
						,rs.getString("content")
						,rs.getInt("readcount"));

				return dog;
			}
		};		

		return getJdbcTemplate().queryForObject(sql, idValue, rowMapper);

	}

	public int updateReadCount(int id) {

		String sql = "";
		sql = "UPDATE dog SET readcount = readcount + 1 WHERE id=?";

		Object[] idValue = new Object[]{
				id	
		};	

		return getJdbcTemplate().update(sql, idValue);

	}

	public int insertDog(Dog dog) {

		String sql = "INSERT INTO dog VALUES(dog_seq.nextval,?,?,?,?,?,?,?,?)";

		Object[] dogValues = new Object[]{
				dog.getKind(),dog.getPrice(),dog.getImage(),dog.getCountry(),dog.getHeight(),dog.getWeight(),dog.getContent(),dog.getReadcount()	
		};	

		return getJdbcTemplate().update(sql, dogValues);

	}

}
