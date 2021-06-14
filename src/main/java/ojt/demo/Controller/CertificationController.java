package ojt.demo.Controller;

import ojt.demo.Entity.Certification;
import ojt.demo.MessageError;
import ojt.demo.Repository.CertificationRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CertificationController
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
@RequestMapping("/api/certification")
public class CertificationController {

    private final CertificationRepository certificationRepository;

    final MessageError messErr;

    /**
     * constructor CertificationController
     *
     * @param certificationRepository,messErr
     */
    public CertificationController(CertificationRepository certificationRepository, MessageError messErr) {
        this.certificationRepository = certificationRepository;
        this.messErr = messErr;
    }

    /**
     * get all certifications
     *
     * @return List<Certification>
     */
    @GetMapping("")
    public List<Certification> getAllCertification() {
        return certificationRepository.findAll();
    }

    /**
     * create certification
     *
     * @param certification
     * @return Certification
     */
    @PostMapping("")
    public Certification createCertification(@RequestBody Certification certification) {
        return certificationRepository.save(certification);
    }

    /**
     * get certification by id
     *
     * @param id
     * @return ResponseEntity<Certification>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable Integer id) {

        Certification certification = certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));
        return ResponseEntity.ok(certification);
    }

    /**
     * update certification by id
     *
     * @param id, certificationUpdate
     * @return ResponseEntity<Certification>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Certification> updateCertification(@PathVariable Integer id, @RequestBody Certification certificationUpdate) {
        Certification certification = certificationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(messErr.getNotFound()));

        certification.setId(certificationUpdate.getId());
        certification.setName(certificationUpdate.getName());

        Certification updateCertification = certificationRepository.save(certification);
        return ResponseEntity.ok(updateCertification);
    }
}
