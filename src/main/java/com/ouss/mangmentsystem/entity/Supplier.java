package com.ouss.mangmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Project Name: MangmentSystem
 * File Name: Supplier
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * Supplier is a part of the MangmentSystem project.
 */


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;

    private String address;
}
