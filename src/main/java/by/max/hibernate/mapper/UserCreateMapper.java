package by.max.hibernate.mapper;

import by.max.hibernate.dao.CompanyRepository;
import by.max.hibernate.dto.UserCreateDto;
import by.max.hibernate.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .personalInfo(object.personalInfo())
                .username(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId())
                        .orElseThrow(IllegalStateException::new))
                .build();
    }
}
