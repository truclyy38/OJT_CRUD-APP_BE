package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Semester
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
@Table(name = "semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "SemesterID")
    private int id;

    @Column(nullable = false, name = "Semester")
    private int semester;

    @Column(nullable = false, name = "Class_Id")
    private int classId;

    @Column(nullable = false, name = "CourseID")
    private int courseId;

}

