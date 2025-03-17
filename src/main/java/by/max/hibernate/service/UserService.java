package by.max.hibernate.service;


import by.max.hibernate.dao.UserRepository;
import by.max.hibernate.dto.UserCreateDto;
import by.max.hibernate.dto.UserReadDto;
import by.max.hibernate.entity.User;
import by.max.hibernate.mapper.UserCreateMapper;
import by.max.hibernate.mapper.UserReadMapper;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;

import javax.validation.*;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    public Long create(UserCreateDto userCreateDto) {
        // validation
        @Cleanup ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserCreateDto>> validationResult = validator.validate(userCreateDto);
        if (!validationResult.isEmpty()){
            throw new ConstraintViolationException(validationResult);
        }


        User userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }


    //todo
    public boolean update(Long id, UserCreateDto updatedUser) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.update(userCreateMapper.mapFrom(updatedUser)));
        return maybeUser.isPresent();
    }
}
