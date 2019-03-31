package pl.dopierala.Repository;

import pl.dopierala.Domain.User;
import pl.dopierala.Domain.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepoInMemory implements UserRepo {
    private static int id_generator = 0;
    private List<User> users = new ArrayList<>();

    public UserRepoInMemory() {
        User u1 = new User("Piotr","Buc", 33, true);
        User u2 = new User("Kamil","Purator", 26, true);
        User u3 = new User("Patryk","Dybek", 29, true);
        User u4 = new User("Tomasz","Kulas", 27, true);
        User u5 = new User("Wiesława","Pieseł", 24, false);
        User u6 = new User("Anita","Chmielnicka", 54, false);
        User u7 = new User("Stefan","Peterhanzel", 54, true);

        this.saveUser(u1);
        this.saveUser(u2);
        this.saveUser(u3);
        this.saveUser(u4);
        this.saveUser(u5);
        this.saveUser(u6);
        this.saveUser(u7);
    }

    @Override
    public List<User>getUsers(){
        return users;
    }

    @Override
    public void saveUser(User usr){
        usr.setId(id_generator++);
        users.add(usr);
    }

    @Override
    public User getById(int id) {
        Optional<User> first = users.stream().filter(u -> u.getId() == id).findFirst();
        return first.orElse(null);
    }

    @Override
    public User deleteUserById(int id) {
        User usrToDelete = getById(id);
        users.remove(usrToDelete);
        return usrToDelete;
    }

    @Override
    public User updateUserById(int id, UserDTO usrData) {
        User usrToUpdate = getById(id);
        if(usrToUpdate == null){
            return null;
        }else{
            if(usrData.lastName != null){
                usrToUpdate.setLastName(usrData.lastName);
            }
            if(usrData.name != null){
                usrToUpdate.setName(usrData.name);
            }
            if(usrData.age != null){
                usrToUpdate.setAge(usrData.age);
            }
            if(usrData.sex != null){
                usrToUpdate.setSex(usrData.sex);
            }
            return usrToUpdate;
        }
    }
}
