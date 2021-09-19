package ru.job4j.forum.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"title"})
public class Role implements GrantedAuthority {
    @Id
    private long id;
    private String title;

    public static Role of(String title) {
        Role roleClass = new Role();
        roleClass.setTitle(title);
        return roleClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getAuthority() {
        return title;
    }
}
