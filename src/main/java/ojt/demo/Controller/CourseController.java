package ojt.demo.Controller;

import ojt.demo.Entity.Course;
import ojt.demo.Exception.APIRequestException;
import ojt.demo.MessageError;
import ojt.demo.Repository.CourseRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseController
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
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseRepository courseRepository;

    final MessageError messErr;

    /**
     * constructor CourseController
     *
     * @param courseRepository,messErr
     */
    public CourseController(CourseRepository courseRepository, MessageError messErr) {
        this.courseRepository = courseRepository;
        this.messErr = messErr;
    }

    /**
     * get all courses for Course page
     *
     * @return List<Course>
     * @throw APIRequestException
     */
    @GetMapping("")
    public List<Course> getAllCourse() {
        List<Course> courses;
        try {
            courses = courseRepository.findAll();
        } catch (Exception e) {
            throw new APIRequestException(messErr.getApiRequest());
        }
        return courses;
    }

    /**
     * create course for New Course page
     *
     * @param course
     * @return Course
     */
    @PostMapping("")
    public Course createCourse(@RequestBody Course course) {

        return courseRepository.save(course);
    }

    /**
     * get course by id
     *
     * @param id
     * @return Course
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        return ResponseEntity.ok(course);
    }

    /**
     * get course by major for CourseByMajor page
     *
     * @param majorId
     * @return List<Course>
     */
    @GetMapping("/major/{majorId}")
    public List<Course> getCourseByMajorId(@PathVariable Integer majorId) {

        return courseRepository.findCoursesByMajorid(majorId);
    }

    /**
     * get course by semester for filter course in CourseByMajor Page
     *
     * @param semester
     * @return List<Course>
     */
    @GetMapping("/semester/{semester}")
    public List<Course> getCourseBySemester(@PathVariable Integer semester) {

        return courseRepository.findCoursesBySemester(semester);
    }

    /**
     * get course by semester and majorId for CourseByClass page
     *
     * @param semester, majorId
     * @return List<Course>
     */
    @GetMapping("/major/{majorId}/semester/{semester}")
    public List<Course> getCourseBySemester(@PathVariable Integer semester, @PathVariable Integer majorId) {

        return courseRepository.findCoursesBySemesterAndMajorid(semester, majorId);
    }

    /**
     * update course by id
     *
     * @param id, courseUpdate
     * @return List<Course>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseUpdate) {
        Course course = courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));

        course.setId(courseUpdate.getId());
        course.setName(courseUpdate.getName());
        course.setCode(courseUpdate.getCode());
        course.setMajorid(courseUpdate.getMajorid());
        course.setSemester(courseUpdate.getSemester());

        Course updateCourse = courseRepository.save(course);
        return ResponseEntity.ok(updateCourse);
    }

    /**
     * delete course by id
     *
     * @param id
     * @return ResponseEntity
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourses(@PathVariable Integer id) {
        Course course = courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
