package core.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "db", name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String password;
}
