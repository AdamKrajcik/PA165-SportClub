package cz.muni.fi.pa165.sportsclub.dto;

import java.util.Objects;

/**
 * User DTO
 *
 * @author 422636 Adam Krajcik
 */
public class UserDto {

    private Long id;

    private String email;

    private String role;

    private String passwordHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;
        return Objects.equals(getId(), userDto.getId()) &&
                Objects.equals(getEmail(), userDto.getEmail()) &&
                Objects.equals(getRole(), userDto.getRole());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
