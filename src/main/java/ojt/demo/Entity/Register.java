package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Register
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
@Table(name = "register")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "RegisterID")
    private int id;

    @Column(nullable = false, name = "ClassID")
    private int classId;

    @Column(nullable = false, name = "UserID")
    private int userId;
}





