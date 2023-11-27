package com.workshopmongo.repositories;

import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
  List<Post> findByTitleContainingIgnoreCase(String text);

  // @Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, "
  // + "{ $or: [ { title: { $regex: ?0, $options: 'i' } }, { body: { $regex: ?0, $options: 'i' } },
  // "
  // + "{ comments.text: { $regex: ?0, $options: 'i' } } ] } ] }")
  // List<Post> searchByTextAndDate(String text, Instant startDate, Instant endDate);

  @Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, { $or: [ { title: { $regex: ?0, $options: 'i' } }, { body: { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } }]} ] }")
  List<Post> searchByTextAndDate(String text, Instant startDate, Instant endDate);
}
