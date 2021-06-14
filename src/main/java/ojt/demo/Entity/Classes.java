package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Classes
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
@Table(name = "classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id")
    private int id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "semester")
    private int semester;

    @Column(nullable = false, name = "majorid")
    private int majorid;

    @Column(nullable = false, name = "total")
    private int total;

}

