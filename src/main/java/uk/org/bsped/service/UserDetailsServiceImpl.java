package uk.org.bsped.service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.org.bsped.dao.UserDao;
import uk.org.bsped.domain.CurrentUser;
import uk.org.bsped.model.Users;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
	static Logger log = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) {
        log.info("Authenticating user with username: " + username);
        Users user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not found"));
        }
        return new CurrentUser(user);
    }
}