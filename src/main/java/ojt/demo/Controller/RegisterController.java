package ojt.demo.Controller;

import ojt.demo.Entity.Register;
import ojt.demo.MessageError;
import ojt.demo.Repository.RegisterRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * RegisterController
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
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterRepository registerRepository;

    final MessageError messErr;

    /**
     * constructor RegisterController
     *
     * @param registerRepository,messErr
     */
    public RegisterController(RegisterRepository registerRepository, MessageError messErr) {
        this.registerRepository = registerRepository;
        this.messErr = messErr;
    }

    /**
     * get all register
     *
     * @return List<Register>
     */
    @GetMapping("")
    public List<Register> getAllRegister() {

        return registerRepository.findAll();
    }

    /**
     * create a register
     *
     * @return ResponseEntity<?>
     * @Param register, br
     */
    @PostMapping("")
    public ResponseEntity<?> createRegister(@RequestBody @Valid Register register, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(br.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        if (registerRepository.existsByUserIdAndClassId(register.getUserId(), register.getClassId())) {
            return new ResponseEntity<>(messErr.getRegisterErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        registerRepository.save(register);
        return new ResponseEntity<>(messErr.getAddSuccess(), HttpStatus.OK);
    }

    /**
     * get a register by id
     *
     * @return ResponseEntity<Register>
     * @Param id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Register> getRegisterById(@PathVariable Integer id) {

        Register register = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        return ResponseEntity.ok(register);
    }

    /**
     * update a register by id
     *
     * @return ResponseEntity<Register>
     * @Param id, registerUpdate
     */
    @PutMapping("/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable Integer id, @RequestBody Register registerUpdate) {
        Register register = registerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));

        register.setId(registerUpdate.getId());
        register.setClassId(registerUpdate.getClassId());
        register.setUserId(registerUpdate.getUserId());

        Register updateRegister = registerRepository.save(register);
        return ResponseEntity.ok(updateRegister);
    }

    /**
     * delete a register by id
     *
     * @return ResponseEntity
     * @Param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRegisters(@PathVariable Integer id) {
        Register register = registerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        registerRepository.delete(register);
        Map<String, Boolean> response = new HashMap<>();
        response.put(messErr.getDeleteSuccess(), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
