import java.sql.*;
import java.util.Scanner;

public class Functions {
    // Initial values
    //int id = 3006;

    /**
     * Creates the connection to the database
     * @return the connection to the database
     */
    public static Connection connect()
    {
        try
        {
            String server = "localhost";
            String db = "ebookstore";
            String port = "3325";
            String user = "root";
            String password = "Incognito13";

            String hostname = "jdbc:mysql://" + server +":" + port + "/" + db +"?useSSL=false";

            // Connects to the database
            Connection myConn = DriverManager.getConnection(hostname, user, password);

            return myConn;

        }catch (SQLException e)
        {
            System.out.println("Error in connecting to the database");
            e.printStackTrace();
        }
        return null;
    }

    public static void createDatabase()
    {

        try
        {
            Connection connection = connect();

            // Uses the database
            Statement statement = connection.createStatement();

            // Creates the database
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS ebookstore");

            // Execute query
            statement.executeUpdate("USE ebookstore");

            // Create table query
            String createTable = "CREATE table IF NOT EXISTS books " +
                    "CREATE TABLE BOOKS (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " title VARCHAR(50), author VARCHAR(50), qty INT)";

            // Create table
            statement.executeUpdate(createTable);

        }catch (SQLException e)
        {
            System.out.println("Error in creating database");
            e.printStackTrace();
        }
    }


    /**
     * This method adds a new book into the library.
     * This
     */
    public static void addBook()
    {
        Scanner input = new Scanner(System.in);

        try
        {
            // Connects to the database
            Connection connection = connect();

            //int id = 3008;
            int qty;
            String title, author;
            boolean status = true;

            // Gathers the title of the book
            System.out.println("Please enter the title of the book: ");
            title = input.nextLine();

            // Gathers the book authors name
            System.out.println("Please enter the author of the book: ");
            author = input.nextLine();

            // Gets the quantity of the book
            System.out.println("Please enter the quantity of the book in stock: ");
            qty = input.nextInt();

            // Query statement to insert into the database
            String query = "INSERT INTO books(title, author, qty) VALUES ( ?, ?, ?)";

            // Prepared statement to dynamically add data input from the user
            PreparedStatement statement = connection.prepareStatement(query);

            // User input's for the query.
            //statement.setInt(1, id);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, qty);

            // Executes the query.
            statement.executeUpdate();

            // Notifies the user that the record entry has been successful
            System.out.println("Book details successfully entered into the library");
        } catch(SQLException e)
        {
            System.out.println("Error in inserting data");
            e.printStackTrace();
        }
    }


    /**
     * This method updates a single book selected by the user based on the books Id
     * All books are displayed to the user
     * The user is then prompted to select the detail they would like to update Title, Author or Quantity
     * of the selected book
     */
    public static void updateBook()
    {
        Scanner input = new Scanner(System.in);

        try
        {
            // Connect to the database
            Connection connection = connect();

            // Line to connect to the database for running queries
            Statement statement = connection.createStatement();

            // Displays all the books in the library
            displayBooks(statement);

            // Prompt the user to update data
            System.out.println("\nWhat book details would you like to change? " +
                    "Please enter the Book's ID");
            int bookId = input.nextInt();

            // Displays the book selected
            displayBook(statement, bookId);

            // Prompts the user to select what detail of the book to update
            System.out.println("What detail would you like to update?\n1 - Title  2 - Author  3 - Quantity");
            int updateChoice = input.nextInt();
            input.nextLine();

            switch(updateChoice)
            {
                case 1:

                    // Prompts user for input
                    System.out.println("Please update the title of the book:");
                    String updatedTitle = input.nextLine();

                    // Query to update the title of the book
                    String updateTitleQuery = "UPDATE books SET title=? WHERE id=?";

                    // Prepared statement with query for execution
                    PreparedStatement statement1 = connection.prepareStatement(updateTitleQuery);

                    // Prepared Statements with values for each parameter
                    statement1.setString(1, updatedTitle);
                    statement1.setInt(2, bookId);

                    // Executes the query
                    statement1.executeUpdate();
                    System.out.println("Book title updated successfully");

                    // Displays the updated book details
                    displayBook(statement, bookId);

                    // Close the connections
                    statement1.close();
                    break;

                case 2:
                    // Promts user for input
                    System.out.println("Please update the author of the book:");
                    String updatedAuthor = input.nextLine();

                    // Query to update the author of the book
                    String updateAuthorQuery = "UPDATE books SET author=? WHERE id=?";

                    // Prepared statement with query for execution
                    PreparedStatement statement2 = connection.prepareStatement(updateAuthorQuery);

                    // Prepared Statements with values for each parameter
                    statement2.setString(1, updatedAuthor);
                    statement2.setInt(2, bookId);

                    // Executes the query
                    statement2.executeUpdate();
                    System.out.println("Book author updated successfully");

                    // Displays the updated book details
                    displayBook(statement, bookId);

                    // Close connection
                    statement2.close();
                    break;

                case 3:
                    // Promts user for input
                    System.out.println("Please update the quantity of the book:");
                    String updatedQty = input.nextLine();

                    // Query to update the author of the book
                    String updateQtyQuery = "UPDATE books SET qty=? WHERE id=?";

                    // Prepared statement with query for execution
                    PreparedStatement statement3 = connection.prepareStatement(updateQtyQuery);

                    // Prepared Statements with values for each parameter
                    statement3.setString(1, updatedQty);
                    statement3.setInt(2, bookId);

                    // Executes the query
                    statement3.executeUpdate();
                    System.out.println("Book quantity updated successfully");

                    // Displays the updated book details
                    displayBook(statement, bookId);

                    // Close Connection
                    statement3.close();
                    break;

                default:
                    System.out.println("Sorry Incorrect selection made, Please try again");
            }

            // Close connections
            statement.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error in updating data");
            e.printStackTrace();
        }
    }


    public static void searchBook()
    {
        Scanner input = new Scanner(System.in);

        try
        {
            // Connect to the database
            Connection connection = connect();

            // Prompts the user to search the book
            System.out.println("Please enter the name of the book, you would like to check:");
            String bookName = input.nextLine();

            // Search query
            String searchQuery = "SELECT * FROM books WHERE title= ?";

            // Prepared statement
            PreparedStatement statement = connection.prepareStatement(searchQuery);

            // Prepared statement
            statement.setString(1, bookName);

            // Result set
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                System.out.println("Book found:\n");
                System.out.println(result.getInt("id") + ": " + result.getString("title")
                        + ", " + result.getString("author") + ", " + result.getInt("qty"));
            }

            // Close connection
            result.close();
            statement.close();
            connection.close();


        }catch(SQLException e)
        {
            System.out.println("Error in search query");
        }
    }



    /**
     * This method removes a book entry from the library
     * It displays all books inside the library and then prompts the user to enter a book Id to be deleted
     * Then displays all the books back to the user.
     */
    public static void deleteBook()
    {
        Scanner input = new Scanner(System.in);
        try
        {
            // Connect to the database
            Connection connection = connect();

            // Statement
            Statement statement = connection.createStatement();

            // Display all books in the database
            System.out.println("All books in the Database\n");
            displayBooks(statement);
            System.out.println("\n");

            // Prompts user for the for the entry
            System.out.println("Please enter the Book Id of the book you would like to delete:");
            int deletId = input.nextInt();

            String deleteQuery = "DELETE FROM books WHERE id=?";

            // Prepared statement
            PreparedStatement statement1 = connection.prepareStatement(deleteQuery);

            // Prepared statements
            statement1.setInt(1, deletId);

            // Executes query
            statement1.executeUpdate();

            // Displays the message for a deleted book
            System.out.println("Book successfully deleted\n\n");

            // Displays all books
            displayBooks(statement1);

        }catch(SQLException e)
        {
            System.out.println("Error occurred while trying to delete entry");
            e.printStackTrace();
        }
    }


    /**
     *  This method displays all books back to the user
     * @param statement Statement for the query
     * @throws SQLException to catch any errors
     */
    public static void displayBooks(Statement statement)throws SQLException
    {
        ResultSet result = statement.executeQuery("SELECT id, title, author, qty FROM books");

        while (result.next())
        {
            System.out.println(result.getInt("id") + ", "
                    + result.getString("title") + ", "
                    + result.getString("author") + ", "
                    + result.getInt("qty"));
        }
    }


    /**
     * This method displays a single book entry based on the book Id given
     * @param statement Statement
     * @param bookId The id of the book selected
     * @throws SQLException
     */
    public static void displayBook(Statement statement, int bookId) throws SQLException
    {
        // selects the book from the database.
        ResultSet row = statement.executeQuery("SELECT id, title, author, qty FROM books WHERE id=" + bookId);

        // Displays the book selected
        if (row.next())
        {
            System.out.println(row.getInt("id") + ", "
                    + row.getString("title") + ", "
                    + row.getString("author") + ", "
                    + row.getInt("qty"));
        }
    }
}
