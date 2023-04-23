package com.company.dao.impl;

//import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lenovo
 */
public class UserDaoImpl extends AbstractDao implements UserDaoInter {
  
  
 

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "select "
                    + "  u.*,  "
                    + "  n.nationality, "
                    + "  c.name as birthplace  "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where 1=1 ";

            if(name!=null && !name.trim().isEmpty()){
                sql += " and u.name=? ";
            }

            if(surname!=null && !surname.trim().isEmpty()){
                sql += " and u.surname=? ";
            }

            if(nationalityId!=null){
                sql += " and u.nationality_id=? ";
            }


            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if(name!=null && !name.trim().isEmpty()){
                stmt.setString(i, name);
                i++;//2
            }

            if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;//3
            }

            if(nationalityId!=null){
                stmt.setInt(i, nationalityId);
            }


            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
            //    User u = getUser(rs);

             //   result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1,email);
            stmt.setString(2,password);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
          //      result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=?");
            stmt.setString(1,email);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               // result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateUser(User u) {
       EntityManager em = em();
      em.getTransaction().begin();
      em.merge(u);
      em.getTransaction().commit();
      
     
      em.close();
      return true;
    }

    @Override
    public boolean removeUser(int id) {
       EntityManager em = em();
        
        User u = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
        
        em.close();
        return true;
        
    }

    @Override
    public User getById(int userId) {
      EntityManager em = em();
       User u =em.find(User.class, userId);
      
      
     em.close();
       return u;
    }

    private  static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
     u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

     EntityManager em = em();
      em.persist(u);
      em.getTransaction().commit();
     em.close();
      return true;
    }

  // public static void main(String[] args) {
//        User u = new User(0, "test","test","test","test",null,null,null,null);
//        u.setPassword("12345");
//        new UserDaoImpl().addUser(u);

        //System.out.println(crypt.hashToString(4, "12345".toCharArray()));
 //   }

}