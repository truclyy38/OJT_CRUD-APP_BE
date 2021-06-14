package ojt.demo.Repository;

import ojt.demo.Entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassesRepository
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
public interface ClassesRepository extends JpaRepository<Classes, Integer> {

        /**
         * check if classes exist by class name
         * @param name
         * @return boolean
         */
        boolean existsByName(String name);

        /**
         * get classes by class name
         * @param name
         * @return boolean
         */
        Classes getClassesByName(String name);
}
