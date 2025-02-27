package model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private String phone;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String firstName, String email, String password, Role role, String phone) {
        this.name = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;

    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name)  && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(phone, user.phone) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  email, password, role, phone);
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
