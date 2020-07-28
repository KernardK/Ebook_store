import java.util.Scanner;

public class Library extends Functions {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean status = true;
        boolean continueLoop = true;


        System.out.println("Welcome to the ebookstore\nPlease make a selection:");
        while (status)
        {

            // Connects to the database
            connect();

            System.out.println("1. Add a book\n2. Update a book\n3. Delete a book\n4. Search for a book\n0. Exit");

            int menuChoice = input.nextInt();


            // Option to add a book into the database
            if(menuChoice == 1)
            {
                while(continueLoop)
                {
                    addBook();
                    System.out.println("Would you like to add another book?\nY - Yes or N - No");
                    String addMore = input.nextLine();

                    if(addMore.equals("Y"))
                    {
                        continue;
                    }
                    else
                        {
                            continueLoop = false;
                            break;
                        }
                }
            }
            // Option to update a book in the database
            else if(menuChoice == 2)
            {
                while (continueLoop)
                {
                    updateBook();

                    System.out.println("Would you like to make more updates?\nY - Yes or N - No");
                    String updateMore = input.nextLine();

                    if (updateMore.equals("Y"))
                    {
                        continue;
                    }
                    else
                        {
                            continueLoop = false;
                            break;
                        }
                }
            }

            // Option to delete a book from the library
            else if(menuChoice == 3)
            {
                while(continueLoop)
                {
                    deleteBook();

                    System.out.println("Would you like to delete a book from the bookstore?\n Y - Yes or N - No");
                    String deleteMore = input.nextLine();

                    if(deleteMore.equals("Y"))
                    {
                        continue;
                    }
                    else
                    {
                        continueLoop = false;
                        break;
                    }
                }
            }

            // Option to search for a book in the database
            else if(menuChoice == 4)
            {
                while(continueLoop)
                {
                    searchBook();

                    System.out.println("Would you like to search for another book?\nY - Yes or N - No");
                    String searchMore = input.nextLine();

                    if(searchMore.equals("Y"))
                    {
                        continue;
                    }
                    else
                    {
                        continueLoop = false;
                        break;
                    }
                }
            }


            // Exits the program
            if(menuChoice == 0)
            {
                status = false;
                break;
            }
            else
                {
                    continue;
                }
        }
    }
}
