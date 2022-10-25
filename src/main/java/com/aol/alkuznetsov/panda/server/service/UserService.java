package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.UserDto;
import com.aol.alkuznetsov.panda.server.exception.DataNotFoundException;
import com.aol.alkuznetsov.panda.server.mapper.UserMapper;
import com.aol.alkuznetsov.panda.server.model.User;
import com.aol.alkuznetsov.panda.server.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

  public static final String NOT_FOUND_BY_ID_MSG = "User not found in Database for id: ";
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional(readOnly = true)
  public List<UserDto> findAll() {
    log.debug("Retrieving the list of all users as UseDtos");
    return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public UserDto findById(Long id) {
    log.debug("Retrieving user as UseDto by Id {}", id);
    User user = userRepository.findById(id).orElse(null);
    return userMapper.toDto(user);
  }

  @Transactional
  public UserDto create(UserDto userDto) {
    log.debug("Creating new User based on userDto {}", userDto);
    userDto.setUserCode(UUID.randomUUID().toString());
    User savedUser = userRepository.save(userMapper.fromDto(userDto));
    log.debug("Saved new User {}", savedUser);
    return userMapper.toDto(savedUser);
  }

  @Transactional
  public UserDto update(Long id, UserDto userDto) {
    log.debug("Updating User with id {} based on UserDto {}", id, userDto);
    if (!userRepository.existsById(id)) {
      throw new DataNotFoundException(NOT_FOUND_BY_ID_MSG + id);
    }
    User user = userMapper.fromDto(userDto);
    user.setId(id);
    User updatedUser = userRepository.save(user);
    log.debug("Updated user {}", updatedUser);
    return userMapper.toDto(updatedUser);
  }

  @Transactional
  public void deleteById(Long id) {
    log.debug("Deleting User with id {}", id);
    if (!userRepository.existsById(id)) {
      throw new DataNotFoundException(NOT_FOUND_BY_ID_MSG + id);
    }
    userRepository.deleteById(id);
    log.debug("Deleted User with id {}", id);
  }
}
