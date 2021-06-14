package ojt.demo.Controller;

import ojt.demo.Entity.User;
import ojt.demo.Exception.APIRequestException;
import ojt.demo.MessageError;
import ojt.demo.Repository.RegisterRepository;
import ojt.demo.Repository.UserRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import ojt.demo.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * UserController
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
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private RegisterRepository regisRepo;

    final
    MessageError messErr;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    final
    UserServiceImpl userService;

    public UserController(UserRepository userRepository, RegisterRepository regisRepo, MessageError messErr, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.regisRepo = regisRepo;
        this.messErr = messErr;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    /**
     * get data for User page
     *
     * @return List<User>
     * @throws APIRequestException
     */
    @GetMapping("")
    public List<User> getAllUser() {
        List<User> users;
        try {
            users = userRepository.findByStatus(true);
        } catch (Exception e) {
            throw new APIRequestException(messErr.getApiRequest());
        }
        return users;
    }

    /**
     * create User page
     *
     * @return HTTPStatus
     * @Param User, BindingResult
     */
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(br.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByPhone(user.getPhone())) {
            return new ResponseEntity<>(messErr.getPhoneMessage(), HttpStatus.BAD_REQUEST);
        }
        long codeUser;

        // get name + number in db = username, if empty -> none set number
        // check the same name in db
        // position of that name
        String name = user.getName();
        List<User> sameName = userRepository.findByNameContaining(name);
        codeUser = sameName.size();

        if (sameName.isEmpty()) {
            user.setName(name);
        } else {
            user.setName(name + codeUser);
        }

        // set student code= major code + number in major table
        user.setStudentCode(user.getStudentCode() + userRepository.findByMajorId(user.getMajorId()).size());

        user.setEmail(user.getName() + messErr.getEmailTail());

        user.setPassword(bCryptPasswordEncoder.encode(bCryptPasswordEncoder.encode((user.getName() + "@123"))));
        user.setStatus(true);

        userRepository.save(user);
        return new ResponseEntity<>(messErr.getAddSuccess(), HttpStatus.OK);
    }

    /**
     * get User by Id
     *
     * @return HTTPStatus
     * @throws ResourceNotFoundException
     * @Param Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) throws ResourceNotFoundException {
        User user = userRepository.findByIdAndStatus(id, true);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(messErr.getNotFound(), HttpStatus.BAD_REQUEST);
    }

    /**
     * get User by studentCode
     *
     * @return HTTPStatus
     * @throws ResourceNotFoundException
     * @Param code
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getStudentByStudentCode(@PathVariable String code) throws ResourceNotFoundException {

        User user = userRepository.findByStudentCodeAndStatus(code, true);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(messErr.getNotFound(), HttpStatus.BAD_REQUEST);
    }

    /**
     * get List User by roleId
     *
     * @return List<User>
     * @Param roleId
     */
    @GetMapping("/role/{roleId}")
    public List<User> getUserByRoleId(@PathVariable Integer roleId) {

        return userRepository.findUserByRoleIdAndStatus(roleId, true);
    }

    /**
     * get User by name
     *
     * @return User
     * @Param name
     */
    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name) {

        return userRepository.findByNameAndStatus(name, true);
    }

    /**
     * Update User by Id
     *
     * @return HTTPStatus
     * @Param Id, User
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userUpdate) {
        User user = userRepository.findByIdAndStatus(id, true);
        int code = 0;
        if (user == null) {
            return new ResponseEntity<>(messErr.getNotFound(), HttpStatus.BAD_REQUEST);
        }

        if (!user.getPhone().equals(userUpdate.getPhone()) &&
                userRepository.existsByPhone(userUpdate.getPhone())) {
            return new ResponseEntity<>(messErr.getPhoneMessage(), HttpStatus.BAD_REQUEST);
        }

        String name = userUpdate.getName();
        List<User> sameName = userRepository.findByNameContaining(name);
        code = sameName.size();

        // if the fullName update different from fullName in DB -> set Name, StudentCode and Email again
        if (!user.getFullName().equals(userUpdate.getFullName())) {
            user.setFullName(userUpdate.getFullName());
            // check duplicate same name
            if (sameName.isEmpty()) {
                user.setName(name);
            } else {
                user.setName(name + code);
            }

            user.setEmail(user.getName() + messErr.getEmailTail());
        }

        if (user.getRoleId() == 2) {
            user.setCertificationID(userUpdate.getCertificationID());
        }

        user.setStudentCode(userUpdate.getStudentCode());
        user.setDob(userUpdate.getDob());
        user.setGender(userUpdate.getGender());
        user.setMajorId(userUpdate.getMajorId());
        user.setImage(userUpdate.getImage());
        user.setPhone(userUpdate.getPhone());
        user.setPassword(bCryptPasswordEncoder.encode(userUpdate.getPassword()));
        user.setStatus(true);

        userRepository.save(user);

        return new ResponseEntity<>(messErr.getUpdateSuccess(), HttpStatus.OK);
    }

    /**
     * Delete logic User by update status false
     *
     * @return HTTPStatus
     * @Param Id
     */
    @PutMapping("/delete/{delId}")
    public ResponseEntity<?> deleteLogicUser(@PathVariable Integer delId) {

        User userChosen = userRepository.findByIdAndStatus(delId, true);

        if (userChosen == null) {
            return new ResponseEntity<>(messErr.getNotFound(), HttpStatus.BAD_REQUEST);
        }

        if (regisRepo.existsByUserId(delId)) {
            return new ResponseEntity<>(messErr.getUserCanNotDelete(), HttpStatus.BAD_REQUEST);
        }

        userChosen.setStatus(false);
        userRepository.save(userChosen);

        return new ResponseEntity<>(messErr.getDeleteSuccess(), HttpStatus.OK);
    }
}
