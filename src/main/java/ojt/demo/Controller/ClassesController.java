package ojt.demo.Controller;

import ojt.demo.Entity.Classes;
import ojt.demo.Exception.APIRequestException;
import ojt.demo.MessageError;
import ojt.demo.Repository.ClassesRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import ojt.demo.Repository.RegisterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * ClassesController
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
@RequestMapping("/api/classes")
public class ClassesController {

    private final ClassesRepository classesRepository;

    private RegisterRepository registerRepo;

    final MessageError messErr;

    /**
     * constructor ClassesController
     *
     * @param classesRepository,messErr
     * @param registerRepo
     */
    public ClassesController(ClassesRepository classesRepository, RegisterRepository registerRepo, MessageError messErr) {
        this.classesRepository = classesRepository;
        this.registerRepo = registerRepo;
        this.messErr = messErr;
    }

    /**
     * get all classes for Classes page
     *
     * @return List<Classes>
     * @throws APIRequestException
     */
    @GetMapping("")
    public List<Classes> getAllClasses() {
        List<Classes> classes;
        try {
            classes = classesRepository.findAll();
        } catch (Exception e) {
            throw new APIRequestException(messErr.getApiRequest());
        }
        return classes;
    }

    /**
     * create user for Add Class page
     *
     * @param classes,br
     * @return ResponseEntity<?>
     * @throws NullPointerException
     */
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid Classes classes, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(br.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        if (classesRepository.existsByName(classes.getName())) {
            return new ResponseEntity<>(messErr.getNameMessage(), HttpStatus.BAD_REQUEST);
        }
        classesRepository.save(classes);
        return new ResponseEntity<>(messErr.getAddSuccess(), HttpStatus.OK);
    }

    /**
     * get class by name
     *
     * @param name
     * @return Classes
     */
    @GetMapping("/{name}")
    public Classes getClassesByName(@PathVariable String name) {

        return classesRepository.getClassesByName(name);
    }

    /**
     * update class
     *
     * @param id, classesUpdate
     * @return ResponseEntity<Classes>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateClasses(@PathVariable Integer id, @RequestBody Classes classesUpdate) {
        Classes classes = classesRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));

        classes.setId(classesUpdate.getId());
        classes.setName(classesUpdate.getName());
        classes.setTotal(classesUpdate.getTotal());
        classes.setSemester(classesUpdate.getSemester());

        return ResponseEntity.ok(classesRepository.save(classes));
    }

    /**
     * delete class by id
     *
     * @param name
     * @return ResponseEntity
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteClasses(@PathVariable String name) {
        Classes classes = classesRepository.getClassesByName(name);
        if (classes == null) {
            return new ResponseEntity<>(messErr.getNotFound(), HttpStatus.BAD_REQUEST);
        }

        if (registerRepo.existsByClassId(classes.getId())) {
            return new ResponseEntity<>(messErr.getRegisterClassMessage(), HttpStatus.BAD_REQUEST);
        }

        classesRepository.delete(classes);
        return new ResponseEntity<>(messErr.getDeleteSuccess(), HttpStatus.OK);
    }

}
