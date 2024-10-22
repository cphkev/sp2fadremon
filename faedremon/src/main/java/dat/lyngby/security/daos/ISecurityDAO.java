package dat.lyngby.security.daos;

import dk.bugelhartmann.UserDTO;
import dat.lyngby.security.entities.User;
import dat.lyngby.security.exceptions.ValidationException;

public interface ISecurityDAO {
    UserDTO getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password);
    User createUserOG(String username, String password);
}
