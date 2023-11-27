package com.workshopmongo.resources;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.workshopmongo.domain.Post;
import com.workshopmongo.resources.utils.URL;
import com.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

  @Autowired
  private PostService service;

  @GetMapping("/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post post = service.findById(id);
    return ResponseEntity.ok().body(post);
  }

  @GetMapping("/titlesearch")
  public ResponseEntity<List<Post>> findTitle(
      @RequestParam(value = "text", defaultValue = "") String text) {
    text = URL.decodeParam(text);
    List<Post> postList = service.findByTitle(text);
    return ResponseEntity.ok().body(postList);
  }

  @GetMapping("/allsearch")
  public ResponseEntity<List<Post>> searchByTextAndDate(
      @RequestParam(value = "text", defaultValue = "") String text,
      @RequestParam(value = "startDate", defaultValue = "") String startDateString,
      @RequestParam(value = "endDate", defaultValue = "") String endDateString) {
    text = URL.decodeParam(text);
    Instant startDate = URL.stringToDate(startDateString, Instant.parse("2000-01-01T00:00:00.00Z"));
    Instant endDate = URL.stringToDate(endDateString, Instant.now());
    List<Post> postList = service.searchByTextInDate(text, startDate, endDate);
    return ResponseEntity.ok().body(postList);
  }
}
