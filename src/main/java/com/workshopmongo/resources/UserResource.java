package com.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.UserDto;
import com.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    List<User> userList = service.findAll();
    List<UserDto> usersListDto = userList.stream().map(UserDto::new).toList();
    return ResponseEntity.ok().body(usersListDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable String id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(new UserDto(user));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDto userDTO) {
    User user = service.insert(UserDto.toUser(userDTO));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto) {
    userDto.setId(id);
    User user = service.update(UserDto.toUser(userDto));
    return ResponseEntity.ok().body(new UserDto(user));
  }

  @GetMapping("/{id}/posts")
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(user.getPosts());
  }
}
