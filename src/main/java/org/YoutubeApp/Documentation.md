# DOCUMENTATITON

## 1-DTO-Entity-Model Objects
DTO is a simple object used to transfer data between layers. In this application, it is used to get data from the user and display data to the user. For example, when displaying user information, we need to avoid showing sensitive data such as passwords.

Entity is a class that represents a table in the database. It contains fields and behaviors and is more detailed than a DTO.

Model is a class that defines the business logic and rules of an application. It represents the state and behavior of the app. It includes methods to manipulate data, which is retrieved from entities and DTOs. For example, while the Video entity has a userId field, the Video model contains a User object.

## 2-Controller-Service-Repository Layers
Controller Layer: The controller layer serves as a bridge between the user interface and the business logic. It receives data from the user and sends it to the service layer for further processing.

Repository Layer: The repository layer is responsible for interacting with the database. It contains methods for performing CRUD (Create, Read, Update, Delete) operations on the database entities.

### Service Layer
The service layer contains the core business logic of the application. It acts as an intermediary between the controller and the repository. For example, in this application, the service layer is used to validate user input during registration. If a user is already registered with the same username or email, the service prevents the database from saving duplicate credentials.

UserService.checkEmail(String email) -> First, a ConnectionProvider object is created to handle the database connection. The executeQuery method is called with a SQL query to check if an email exists in the tbluser table. The query result is wrapped in an Optional<ResultSet>. If the ResultSet is present, the method checks if there is a matching email by calling rs.next(). If a matching email is found, the method returns true. If no result is found or an exception occurs, it returns false.

UserService.checkUsername(String username) -> First, a ConnectionProvider object is created to handle the database connection. The executeQuery method is called with a SQL query to check if a username exists in the tbluser table. The result of the query is wrapped in an Optional<ResultSet>. If the ResultSet is present, the method checks if there is a matching username by calling rs.next(). If a matching username is found, the method returns true. If no result is found or an exception occurs, it returns false.

UserService.save() -> the user's email is checked first. If the email is unique (i.e., checkEmail() returns false), the checkUsername() method is called to validate the uniqueness of the username. If both the email and username are unique, the user is saved in the repository. If either the email or username already exists, the method returns an empty Optional without saving the user.

UserService.update() -> The method first checks if the user with the given ID exists by calling another method which is findById(). If the user is present it proceeds with the update. If not the method returns an empty Optional.

UserService.delete() -> The method first checks if the user with the given ID exists by calling another method which is findById(). If the user is present it proceeds with the delete and returns true. If not the method returns false.

UserService.findAll() -> The method doesnt have any logic, it just returns userRepository.findAll() method.

UserService.findAllAsDTO() -> First findAll method is called which returns as List. And then another List which holds UserDTO objects is created. Every user data in the first list used for manipulate to create UserDTO object. Finally, each UserDTO is added to the UserDTO list, and the method returns this list of UserDTO objects.

UserService.findByUsernameAsDTO(String username) -> First, the findByUsername method from the userRepository is called, which returns an Optional<User>. If a user with the given username is found, a UserDTO object is created using the data from the retrieved User object. The UserDTO is then wrapped in an Optional and returned. If no user is found, the method returns an empty Optional.

## 3-Utility Package

### A-Connection Provider
Connection, PreparedStatement and ResultSet objects are declared as fields in this class. 

openConnection() -> DriverManager.getConnection() method is called and the URL given as parameter to the method. The parameter URL formed from HOSTNAME, PORT, DB NAME, USERNAME, PASSWORD fields. Which declared as constants in Constant.class. If Connection object is successfully created the method returns true.

closeConnection() -> This method first checks if the Connection object is already closed by using the isClosed() method. If the connection is not closed, the close() method is called to close the connection, and the method returns true.

executeUpdate(String sql) -> The method first call openConnection() method and if its returns true, which means Connection object is created successfully, first it calls prepareStatement(sql), which creates a PreparedStatement object for sending parameterized SQL statements to the database. Then executeUpdate() called which executes the SQL statement in this PreparedStatement object. This method is typically used for SQL statements like INSERT INTO, UPDATE, ALTER, etc. After executing the statement, the method attempts to close the connection by calling closeConnection().

executeQuery(String sql) -> This method first calls the openConnection() method. If it returns true, meaning the connection was successfully established, the prepareStatement(sql) method is called to create a PreparedStatement object for executing the query. The executeQuery() method is then called to execute the SQL query, which returns a ResultSet. The ResultSet is wrapped in an Optional and returned. This method typically used for SQL statements like SELECT etc.

### B-ICRUD
ICRUD<T> This is a generic interface that defines common CRUD (Create, Read, Update, Delete) operations for any type T.

save(T t) -> Takes an object of type T and returns an Optional<T>. This method is used to save the object to the database and returns the saved object wrapped in an Optional if the save operation is successful.

update(T t) -> Takes an object of type T and returns an Optional<T>. This method updates the object in the database and returns the updated object wrapped in an Optional if the update is successful.

delete(Long id) -> Takes an ID of type Long and returns a boolean. This method deletes the object associated with the given ID from the database. It returns true if the deletion is successful, otherwise false.

findAll() -> Returns a List<T> containing all objects of type T from the database.

findById(Long id) -> Takes an ID of type Long and returns an Optional<T>. This method finds and returns the object associated with the given ID, wrapped in an Optional.


### C-SQLQueryBuilder

I- generateInsert(Object entity, String tablename) -> This method generates an SQL INSERT statement for the given entity and table. The class of the entity is retrieved using entity.getClass(), and the fields are extracted using getDeclaredFields(). Two StringBuilder objects (columns and values) are used to hold the columns and corresponding values for the SQL statement. Finally, the method returns a fully formed INSERT INTO SQL statement, combining the table name, columns, and values.

II- generateUpdate(Object entity, String tablename) -> This method generates an SQL UPDATE statement for the given entity and table. Similar to the generateInsert() method, the entity’s class and fields are retrieved. The method loops through the fields, and when it finds a field named id, it retrieves its value to use in the WHERE clause. Finally, the method returns a fully formed UPDATE SQL statement, including the SET clause and WHERE clause with the id.

## 4-Modules

### A-MenuModule

I- firstMenuOpt() -> This method displays the different menu options to the user. The user's selection is taken using the scanner.nextInt() method, which returns the selection as an integer.
II- firstMenuSelection() -> This method contains the switch cases for the menu options displayed in firstMenuOpt(). Based on the user's selection, the appropriate action is taken:
If the user selects 1, the RegisterModule.register() method is called.
If the user selects 2, the LoginModule.login() method is called.
If the user selects 3, the application prints "Exiting..." and exits.
For any other input, an invalid selection message is displayed, and the user is asked to try again.

III- homeMenuOpt() ->  This method displays the different menu options to the user after user logged in. The user's selection is taken using the scanner.nextInt() method, which returns the selection as an integer.
IV- homeMenuSelection() -> This method contains the switch cases for the home menu options displayed in homeMenuOpt(). Based on the user's selection, the appropriate action is taken:
If the user selects 1, the Videomodule.videoSearch() method is called.
If the user selects 2, the VideoModule.videoUpload() method is called.
If the user selects 3, the VideoModule.displayVideoMenu() method is called.
If the user selects 4, the UserModule.myProfile() method is called.
If the user selects 9, the MenuModule.user setted to null and loggedout and returns to the firstMenu().
If the user selects 0, the application prints "Exiting..." and exits.
For any other input, an invalid selection message is displayed, and the user is asked to try again.

### B-RegisterModule
I-register() -> The method starts by displaying the register menu. It asks for the user's name. It asks for the name, surname, email, username, and password. While entering the email, it checks if the email is already taken by calling userService.checkEmail(email). If the email is taken, the user is asked to enter a different one, repeating until a valid email is provided. Similarly, the username is checked for uniqueness using userService.checkUsername(username). If the username is already taken, the user is prompted to provide a different one. After all valid information is gathered, the userController.save() method is called to save the new user with the role set to ERole.USER.

### C-LoginModule

I- login() -> This method takes username and password from user. Then it calls doLogin() method which returns an Optional<User>, if the user is present User defined in MenuModule setted to this user which just logged in. If not it asks credentials again and again until invalidCredentials boolean is set to false.

II- doLogin() ->  This method takes username and password as parameters and uses an SQL SELECT statement with a WHERE clause to get the user from the database. If a user is found in the database with the matching credentials, it retrieves the user's data (such as id, username, password, email, name, surname, and role) and returns an Optional<User>.

### D-VideoModule

I- videoSearch() -> This method displays a menu for searching videos. The user can choose to search by title, category, or date. There’s also an option to go back to the home menu.

II- videoSearchSelection(Integer choice) -> This method processes the user's choice from the videoSearch() menu:
If the user selects 1, the searchByTitle() method is called to allow searching by video title.
If the user selects 2, the searchByCategory() method is called to search by category.
If the user selects 3, the searchByDate() method is called to search by a specific date range.
If the user selects 4, used for returning to the home menu.
For any other input, an invalid choice message is displayed, and the method prompts the user to try again.

III- searchByDate() -> This method prompts the user to enter a start date and an end date in the format YYYY-MM-DD. It then calls videoController.findByDate(startDate, endDate) to retrieve the videos within the given date range.

IV- searchByCategory() -> This method displays a list of categories (Music, Sports, Technology, Games, Entertainment) for the user to choose from.

V- searchByTitle() -> This method allows the user to search for videos by title.

VI- videoInteractionMenu(Video video) -> This method provides a menu for interacting with a specific video. The user can choose to:
Like the video (increases the like count and updates the video).
Dislike the video (increases the dislike count and updates the video).
Add a comment to the video.

VII- incrementViewCount(Video video) -> This method increments the view count for a video by 1 and updates the video using videoController.update().

VIII- videoToModel(Video video) -> This method converts a Video object into a VideoModel object. It held additional information such as the uploader, comments List etc. then constructs and returns a VideoModel object.

IX- askUserForDisplay(List<Video> videos) -> This method displays the search results from a list of videos. It shows the title, view count, like count, and dislike count for each video. The user can select a video to view or interact with by entering the corresponding number. If the user selects a valid video, the incrementViewCount() method is called, followed by videoToModel() to convert the video into a model for display. Finally, the videoInteractionMenu() method is called to provide interaction options for the selected video.

X- videoUpload() -> This method allows the user to upload a video. It asks the user if they want to upload a video, and if they agree, the user is prompted to enter the video's title and description. The user is then asked to choose a category from a list (Music, Sports, Technology, Games, Entertainment). Finally, the video is saved using videoController.save().

XI- displayVideoMenu() -> This method displays the video filter menu. The user is presented with several options to list videos:
By alphabetical order, By view count, By most liked, By most disliked

XII- displayVideoSelection(Integer selection) -> This method handles the user's selection from the displayVideoMenu():
If the user selects 1, the videos are retrieved in alphabetical order using videoController.findAllByTitle().
If the user selects 2, the videos are retrieved by view count using videoController.findAllByViewCount().
If the user selects 3, the videos are retrieved by the number of likes using videoController.findAllByLikeCount().
If the user selects 4, the videos are retrieved by the number of dislikes using videoController.findAllByDislikeCount().


### E-UserModule

I- myProfile() -> This method displays the profile menu, showing different options for updating the profile, displaying the user's videos, likes, comments, or returning to the home menu. The user's selection is taken using scanner.nextInt() and returned.

II- myProfileSelection(Integer choice) -> This method processes the user’s selection from the myProfile() menu:
If the user selects 1, the updateProfile() method is called and show another menu.
If the user selects 2, displayMyVideos() is called to show the user's videos.
If the user selects 3, displayMyLikes() is called to display the likes by the user.
If the user selects 4, displayMyComments() is called to show the user's comments.
If the user selects 5, the user is returned to the home menu.
If an invalid choice is made, the user is prompted to try again.

III- updateProfile() -> This method displays the options for updating the user’s profile. It allows the user to choose between changing their username, password, email, or returning to the home menu. The user's selection is taken using scanner.nextInt() and returned.

IV- updateProfileSelection(Integer choice) -> This method processes the user’s choice from updateProfile():
If the user selects 1, changeUsername() is called to update their username.
If the user selects 2, changePassword() is called to update their password.
If the user selects 3, changeEmail() is called to update their email.
If the user selects 4, they are returned to the home menu.
If an invalid choice is made, the method prompts the user to try again.

V- displayMyVideos() -> This method retrieves the videos uploaded by the current user. The result is passed to the askUserForDisplay() method, which allows the user to view or interact with their videos.

VI- displayMyLikes() -> This method retrieves the likes made by the current user.

VII- displayMyComments() -> This method retrieves the comments made by the current user.

VIII- usersVideoInteraction(Video selectedVideo) -> This method displays a menu with options to display interactions (views, likes, dislikes), update, or delete the selected video.


IX- interactionSelection(Integer choice, Video selectedVideo) -> This method processes the user’s choice for interacting with a selected video:
If the user selects 1, the displayMyInteractions() method is called.
If the user selects 2, the updateVideo() method is called to update the video.
If the user selects 3, the deleteVideo() method is called to delete the video.
If the user selects 4, the user is returned to the home menu.

X- displayMyInteractions(Video video) -> This method displays the interactions (views, likes, dislikes) for a given video.

XI- updateVideo(Video video) -> This method allows the user to update a video's title and description.

XII- deleteVideo(Video video) -> This method deletes the selected video using videocontroller.delete().