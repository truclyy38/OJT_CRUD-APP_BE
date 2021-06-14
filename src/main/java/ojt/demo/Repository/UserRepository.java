package ojt.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ojt.demo.Entity.User;

import java.util.List;

/**
 * UserRepository
 *
 * Version 1.0
 *
 * Date: 06-10-2021
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 06-10-2021	         LyNTT9           Create
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find user by roleId and status
     * @param roleId,status
     * @return List<User>
     */
    List<User> findUserByRoleIdAndStatus( Integer roleId,
                                          boolean status);

    /**
     * find user by studentCode and status
     * @param studentCode,status
     * @return User
     */
    User findByStudentCodeAndStatus(String studentCode, boolean status);

    /**
     * find user by id and status
     * @param id,status
     * @return User
     */
    User findByIdAndStatus( int id, boolean status);

    /**
     * find all user status
     * @param status
     * @return List<User>
     */
    List<User> findByStatus(Boolean status);

    /**
     * find user by name and status
     * @param name,status
     * @return User
     */
    User findByNameAndStatus(String name, boolean status);

    /**
     * find user contain name in db
     * @param name
     * @return List<User>
     */
    List<User> findByNameContaining(String name);

    /**
     * find user by majorId
     * @param majorId
     * @return List<User>
     */
    List<User> findByMajorId(int majorId);
    boolean existsByPhone(String phone);
}
