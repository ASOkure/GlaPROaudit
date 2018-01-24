package uk.org.bsped.dao;

import uk.org.bsped.model.Users;

public interface UserDaoExt {
    Users findUserByUsername(String username);
}
