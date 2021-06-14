package ojt.demo.Controller;

import ojt.demo.Entity.Major;
import ojt.demo.Exception.APIRequestException;
import ojt.demo.MessageError;
import ojt.demo.Repository.MajorRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MajorController
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
@RequestMapping("/api/major")
public class MajorController {

    private final MajorRepository majorRepository;

    final MessageError messErr;

    /**
     * constructor MajorController
     *
     * @param majorRepository,messErr
     */
    public MajorController(MajorRepository majorRepository, MessageError messErr) {
        this.majorRepository = majorRepository;
        this.messErr = messErr;
    }

    /**
     * get all majors
     *
     * @return List<Course>
     * @throw APIRequestException
     */
    @GetMapping("")
    public List<Major> getAllMajor() {

        List<Major> majors;
        try {
            majors = majorRepository.findAll();
        } catch (Exception e) {
            throw new APIRequestException(messErr.getApiRequest());
        }
        return majors;
    }

    /**
     * create major
     *
     * @param major
     * @return Major
     */
    @PostMapping("")
    public Major createMajor(@RequestBody Major major) {
        return majorRepository.save(major);
    }

    /**
     * get major by id
     *
     * @param id
     * @return ResponseEntity<Major>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable Integer id) {

        Major student = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        return ResponseEntity.ok(student);
    }

    /**
     * update major by id
     *
     * @param id, majorUpdate
     * @return ResponseEntity<Major>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Major> updateMajor(@PathVariable Integer id, @RequestBody Major majorUpdate) {
        Major major = majorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));

        major.setId(majorUpdate.getId());
        major.setName(majorUpdate.getName());

        Major updateMajor = majorRepository.save(major);
        return ResponseEntity.ok(updateMajor);
    }

    /**
     * delete major by id
     *
     * @param id
     * @return ResponseEntity
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMajors(@PathVariable Integer id) {
        Major major = majorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        majorRepository.delete(major);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}