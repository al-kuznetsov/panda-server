package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.UserDto;
import com.aol.alkuznetsov.panda.server.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}/users")
@RequiredArgsConstructor
public class UserRestController {
  private final UserService userService;

  @GetMapping
  public List<UserDto> findAll() {
    return userService.findAll();
  }

  @GetMapping("/findAllByNameOrJobTitleContainingIgnoreCase")
  public List<UserDto> findAllByNameOrJobTitleContainingIgnoreCase(
      @Param("searchKey") String searchKey) {
    return userService.findAllByNameOrJobTitleContainingIgnoreCase(searchKey);
  }

  @GetMapping("/{id}")
  public UserDto findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  public UserDto create(@RequestBody UserDto userDto) {
    return userService.create(userDto);
  }

  @PutMapping("/{id}")
  public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
    return userService.update(id, userDto);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }
}
