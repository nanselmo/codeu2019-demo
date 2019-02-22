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

package com.google.codeu.servlets;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Book;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Handles fetching and saving {@link Message} instances. */
@WebServlet("/seedbooks")
public class BookDataServlet extends HttpServlet {

  private Datastore datastore;
  private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

  @Override
  public void init() {
    datastore = new Datastore();
  }

//ask kevin about https://stackoverflow.com/questions/2291028/a-way-to-convert-appengine-datastore-entity-to-my-object/4630126#4630126
  public void storeBook(String inputTitle, String inputAuthor, Integer inputVotes) {
    //creates book instance (with a random UUID)
    Book book = new Book(inputTitle, inputAuthor, inputVotes);

    //takes intance and turns it into Entity
    Entity bookEntity= new Entity("Book", book.getId().toString());
    bookEntity.setProperty("title", book.getTitle());
    bookEntity.setProperty("author", book.getAuthor());
    bookEntity.setProperty("votes", book.getVotes());
    //stores the entity in datastore
    datastoreService.put(bookEntity);
  }

  public void seedBooks(){

    storeBook("The Best We Could Do", "Thi Bui", 6);
    storeBook("Sing, Unburied, Sing", "Jesmyn Ward", 10);
    storeBook("The Book of Unknown Americans", "Cristina Henriquez", 7);
    storeBook("The 57 Bus", "Dashka Slater", 4);
    storeBook("The Handmaid's Tale", "Margaret Atwood", 8);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String msg = "";

    List<Book> all_books = datastore.getBooks();
    if (all_books.size()==0) {
      seedBooks();
      msg = "added books from BookDataServlet";
    }
    else {
      msg = "there are already books in the datastore";
    }
    response.getWriter().println(msg);
  }


}
