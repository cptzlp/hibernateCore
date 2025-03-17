package by.max.hibernate.mapper;

import by.max.hibernate.dto.CompanyReadDto;
import by.max.hibernate.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{

    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(),
                object.getName());
    }
}
