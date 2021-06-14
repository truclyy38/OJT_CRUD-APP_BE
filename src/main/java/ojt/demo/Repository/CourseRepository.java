package ojt.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ojt.demo.Entity.Course;
import java.util.List;

/**
 * CourseRepository
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
public interface CourseRepository extends JpaRepository<Course, Integer> {

        /**
         * get courses by majorid
         * @param majorid
         * @return List<Course>
         */
        List<Course> findCoursesByMajorid(Integer majorid) ;

        /**
         * get courses by semester
         * @param semester
         * @return List<Course>
         */
        List<Course> findCoursesBySemester(Integer semester);

        /**
         * get courses by semester and majorid
         * @param semester, majorid
         * @return List<Course>
         */
        List<Course> findCoursesBySemesterAndMajorid(int semester, int majorid) ;
}
