package com.workshopmongo.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshopmongo.domain.Post;
import com.workshopmongo.repositories.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
  @Autowired
  private PostRepository repository;

  public List<Post> findAll() {
    try {
      List<Post> test = repository.findAll();
      System.out.println("======>>>>" + test);
      return repository.findAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Arrays.asList();
  }

  public Post findById(String id) {
    return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
  }

  public Post insert(Post post) {
    return repository.save(post);
  }

  public void delete(String id) {
    Post post = findById(id);
    repository.delete(post);
  }

  public Post update(Post newPost) {
    Post currentPost = findById(newPost.getId());
    postObjectUpdate(currentPost, newPost);
    return repository.save(currentPost);
  }

  private void postObjectUpdate(Post currentPost, Post newPost) {
    currentPost.setTitle(newPost.getTitle());
    currentPost.setAuthor(newPost.getAuthor());
    currentPost.setBody(newPost.getBody());
    currentPost.setDate(newPost.getDate());
  }
}
