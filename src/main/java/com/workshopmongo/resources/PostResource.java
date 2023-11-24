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
import com.workshopmongo.dto.PostDto;
import com.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

  @Autowired
  private PostService service;

  @GetMapping
  public ResponseEntity<List<PostDto>> findAll() {
    List<Post> postList = service.findAll();
    List<PostDto> postListDto = postList.stream().map(PostDto::new).toList();
    return ResponseEntity.ok().body(postListDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> findById(@PathVariable String id) {
    Post post = service.findById(id);
    return ResponseEntity.ok().body(new PostDto(post));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody PostDto postDTO) {
    Post post = service.insert(PostDto.toPost(postDTO));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(post.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> update(@PathVariable String id, @RequestBody PostDto postDto) {
    postDto.setId(id);
    Post post = service.update(PostDto.toPost(postDto));
    return ResponseEntity.ok().body(new PostDto(post));
  }
}
