package com.alz2019.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    UUID id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String body;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return id.equals(notification.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
