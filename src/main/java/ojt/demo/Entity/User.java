package ojt.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * User
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "UserID")
    private int id;

    @Column(nullable = true, name = "Student_Code")
    private String studentCode;

    @Column(nullable = false, name = "User_Name")
    private String name;

    @Column(nullable = false, name = "Full_Name")
    @Length(max = 50, message = "Fullname is too long")
    private String fullName;

    @Column(nullable = false, name = "Major_ID")
    private int majorId;

    @Column(nullable = false, name = "DOB")
    private String dob;

    @Column(nullable = false, name = "Image")
    private String image;

    @Column(nullable = false, name = "Email")
    private String email;

    @Column(nullable = false, name = "Gender")
    private Boolean gender;

    @Column(nullable = false, name = "Phone")
    private String phone;

    @Column(nullable = false, name = "Password")
    private String password;

    @Column(nullable = false, name = "RoleID")
    private int roleId;

    @Column(name = "CertificationID")
    private int certificationID;

    @Column(name = "Status")
    private boolean status;

//    public boolean getStatus() {
//    }
}



