package by.max.hibernate.dto;

import by.max.hibernate.entity.PersonalInfo;
import by.max.hibernate.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        Role role,
        Integer companyId
) {
}
