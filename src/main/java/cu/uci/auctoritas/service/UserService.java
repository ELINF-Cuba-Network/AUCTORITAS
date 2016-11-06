package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.UserInfo;
import cu.uci.auctoritas.domain.Event;
import cu.uci.auctoritas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bichos on 7/06/16.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Event post(UserInfo userInfo){
        Event event = new Event();
        try {
            userRepository.run(userInfo);
            event.setEvent("User created");
            return event;
        } catch (Exception e) {
            String s=e.getCause().getMessage().toString();
            event.setEvent(s);
            return event;

        }
    }
    public List<UserInfo> findByname(String name) {

        try {
            return userRepository.getByname(name);
        } catch (Exception e) {
            String s = e.getCause().getMessage().toString();
            List<UserInfo> res = new ArrayList<>();
            UserInfo user = new UserInfo();
            user.setName(s);
            res.add(user);
            return res;
        }
    }
    public Event update(String username, String newpassword){
        Event event = new Event();
        try {
            userRepository.update(username,newpassword);
            event.setEvent("User updated");
            return event;
        } catch (Exception e) {
            String s=e.getCause().getMessage().toString();
            event.setEvent(s);
            return event;
        }

    }
    public Event delete(String username){

        try {
            userRepository.delete(username);
            Event event = new Event();
            event.setEvent("User deleted");
            return event;
        } catch (Exception e) {
            Event event = new Event();
            event.setEvent(e.getCause().getMessage().toString());

            return event;
        }
    }
}
