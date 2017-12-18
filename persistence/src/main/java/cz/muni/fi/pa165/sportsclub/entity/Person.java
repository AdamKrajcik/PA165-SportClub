package cz.muni.fi.pa165.sportsclub.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Abstract class for shared variables
 *
 * @author Jan cech
 * @author 422636 Adam Krajcik updates
 */
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 25, nullable = false)
    private String firstName;

    @NotNull
    @Column(length = 25, nullable = false)
    private String lastName;

    //business key
    @NotNull
    @Column(length = 25, unique = true, nullable = false)
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
