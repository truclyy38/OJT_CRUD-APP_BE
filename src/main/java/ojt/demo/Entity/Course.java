package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "courses")

/**
 * Course
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
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "courseid")
    private int id;

    @Column(nullable = false, name = "course_code")
    private String code;

    @Column(nullable = false, name = "course_name")
    private String name;

    @Column(nullable = false, name = "Majorid")
    private int majorid;

    @Column(nullable = false, name = "Semester")
    private int semester;
}