package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: CategoryDTO
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * CategoryDTO is a part of the MangmentSystem project.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Name is required")

    private String name;




}
