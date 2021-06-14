package ojt.demo.Repository;


import ojt.demo.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RegisterRepository
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
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    boolean existsByUserIdAndClassId(int userId, int classId);

    /**
     * check register exist by id
     * @param integer
     * @return boolean
     */
    @Override
    boolean existsById(Integer integer);

    /**
     * check register exist by userId
     * @param userId
     * @return boolean
     */
    boolean existsByUserId(Integer userId);

    /**
     * check register exist by classId
     * @param classId
     * @return boolean
     */
    boolean existsByClassId(Integer classId);

}
