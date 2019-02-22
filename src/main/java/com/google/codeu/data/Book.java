/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import java.util.UUID;

/** A single book **/
public class Book {
  //list the object's attributes
  private UUID id;
  private String title;
  private String author;
  private Integer votes;


  /** the constructor methods are all overloaded - which means they can respond to a variable number of parameters,
   in this case to 2, 3, of 4 parameters **/
  //CONSTRUCTOR METHOD: used when new Book() is used for the first time
  //each instance will get a random id, the givn title and author and 0 votes
  public Book(String title, String author) {
    this(UUID.randomUUID(), title, author, 0);
  }

  //CONSTRUCTOR METHOD: used if you want to initalize the Book with a certain amount of votes
  public Book(String title, String author, Integer votes) {
    this(UUID.randomUUID(), title, author, votes);
  }

  //CONSTRUCTOR METHOD: used in getBooks() in Datastore to create a new Book instance from the queried data
  public Book(UUID id, String title, String author, Integer votes) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.votes = votes;
   }

  //GETTER METHODS
  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public long getVotes() {
    return votes;
  }
}
