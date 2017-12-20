package cz.muni.fi.pa165.sportsclub.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author Jan Cech
 * DTO for creating player
 */
public class PlayerCreateDto {

    private Long id;

    @NotNull
    @Min(80)
    @Max(280)
    @Digits(integer = 3, fraction = 0)
    private int height;

    @NotNull
    @Min(30)
    @Max(150)
    @Digits(integer = 3, fraction = 0)
    private int weight;


    @NotNull
    @Past
    private Date dateOfBirth;

    @NotNull
    @Size(min = 1, max = 25)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 25)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 25)
    @Email
    private String email;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
