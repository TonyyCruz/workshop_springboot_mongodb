package com.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.UserDTO;
import com.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> users = service.findAll();
    List<UserDTO> usersDto = users.stream().map(UserDTO::new).toList();
    return ResponseEntity.ok().body(usersDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(new UserDTO(user));
  }

  @PostMapping
  public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
    User user = UserDTO.toUser(userDTO);
    User createdUser = service.insert(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(createdUser.getId()).toUri();
    return ResponseEntity.created(uri).body(new UserDTO(createdUser));
  }
}
