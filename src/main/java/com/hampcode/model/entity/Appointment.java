package com.hampcode.model.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    @OneToOne
    @JoinColumn(name = "availability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_availability"))
    private Availability availability;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_history"))
    @JsonBackReference
    private History history;
}
=======
public class Appointment {
}
>>>>>>> origin/feature/crud-diethistory
