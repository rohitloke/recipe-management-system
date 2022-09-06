# recipe-management-system
Recipe management system using java and spring boot.

The project is divided in 2 modules.
<ol>
<li>`rms-core` - contains core functionality </li>
<li>`rms-api`  - REST interface layer.</li>
</ol>

The core functionality is implemented completely independent of the interface layer hence knows nothing about it. This
allows me to reuse the `rms-core` is in other application.

Each layer of the application has single resposibility with data access limited to Repository classes and business function limited to Service classes.And lastly Controller classes exposing the the data to outside world.

For this application we use embedded `H2` database for both tests and in application.

I employ Search Index for it's full text search ability to search recipes. Using the `hibernate-search-orm` abstraction and the in-built `Lucene search` for indexing and querying I am able to search recipes efficiently and flexibly. This can easily be replaced with Elastic search engine by employing and external indexer that indexes the data. 

I use `spring-test` to test the application. Following the test pyramid model, unit test cases coverage is extensive and relatively limited integration test coverage. I employ
Integration test for Repository, Search index service and Controller classes. All other classes are unit tested using `Junit 5` and `Mockito`. The data setup for integrstion test is done using dbsetup library which provides simple and clean api to insert and delete data.

The API is documented using `springdoc-openapi-ui` library. 

The application misses spring security, though recipe API can be public the User API may need some protection. Also, server side validation is missing.


To run the project:
<ol>
  <li>Clone the project using <code>git clone https://github.com/rohitloke/recipe-management-system.git</code> command</li>
  <li>Navigate to project directory and run `mvn clean install`. This will run tests and build requisite jars</li>
  <li>From the project directory run `java -jar rms-api\target\rms-api-0.0.1-SNAPSHOT.jar` that should start our spring boot application</li>
</ol>

The REST documentation is available on `http://localhost:8080/rms/rms-api-docs.html`. The swagger ui also allows us to make http request for the available REST endpoints.

We add Recipes first .For eg:
<pre><code>curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/rms/recipe --data '{"name": "new recipe", "description":"new description", "veg":"true", "serves":"3", "instructions":"do something", "ingredients":"add something"}'</code> </pre>

and then add user with just created recipe names in the favouriterecipes collection:
<pre><code>curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/rms/user/newuser --data '{"username": "newuser", "favouriteRecipes":[{"name":"new recipe"}]}'</code> </pre>

Before search it's important to index recipes. User are not indexed:
<pre><code>curl -X GET -i http://localhost:8080/rms/indexing</code></pre>

to search try the following:
<pre><code>curl -X GET -i 'http://localhost:8080/rms/recipe/search?instructions="do "&includeIngredients="add"&excludeIngredients="other"&onlyVeg=true&servings=2'</code></pre>

<h2>Warning</h2> Since both application and test use embedded h2 db, while the application is running with data the test run can have unexpected results. 
