package by.max.hibernate.dto;

import by.max.hibernate.entity.PersonalInfo;
import by.max.hibernate.entity.Role;

public record UserReadDto(
        Long id,
        PersonalInfo personalInfo,
        String username,
        Role role,
        CompanyReadDto company
) {


}
