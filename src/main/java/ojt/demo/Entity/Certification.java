package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Certification
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
@Setter
@Getter
@Entity
@Table(name = "certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "CertificationID")
    private int id;

    @Column(nullable = false, name = "Certification_name")
    private String Name;

}
