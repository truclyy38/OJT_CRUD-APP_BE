package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Role
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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "RoleID")
    private int id;

    @Column(nullable = false, name = "Role_Name")
    private String name;
}
