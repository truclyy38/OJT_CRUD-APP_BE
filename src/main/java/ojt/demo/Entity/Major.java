package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name ="major")

/**
 * Major
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
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="MajorID")
    private int id;

    @Column(nullable = false, name="Major_Name")
    private String name;

    @Column(nullable = true, name="Major_Code")
    private String majorcode;
}
