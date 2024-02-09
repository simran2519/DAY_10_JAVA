package DAY_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lib_mngment {

    abstract class item {
        // Private fields of item class
        int id;
        String title, Category, Author;
        boolean status;

        public item(int id, String title, String Category, String author) {
            this.id = id;
            this.Category = Category;
            this.title = title;
            this.Author = author;
            this.status = true;//By default the status is set to available as a book is added to the book list
        }

        public int getId() {
            return id;
        }

        public String getCategory() {
            return Category;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return Author;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public abstract void display();
    }

    class books extends item {
        private String isbn;

        public books(int id, String title, String category, String author, String isbn) {
            super(id, title, category, author);
            this.isbn = isbn;
        }

        public String getIsbn() {
            return isbn;
        }

        @Override
        public void display() {
            String statusStr = status ? "Available" : "Checked Out";
            System.out.println("     Book ID: " + getId() + ", Title: " + getTitle());
            System.out.println("     Category " + getCategory() + " Author: " + getAuthor() + ", ISBN: " + isbn);
            System.out.println("     Status: " + statusStr);
            System.out.println("            *******************************");

        }
    }

    static class LibraryMember {
        private int memberId;
        private String name;

        // constructor
        public LibraryMember(int memberID, String name) {
            this.memberId = memberID;
            this.name = name;
        }

        // getters
        public int getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }
    }

    class Magazines extends item {
        private int issuenumber;

        public Magazines(int id, String title, String category, String author, int issuenumber) {
            super(id, title, category, author);
            this.issuenumber = issuenumber;
        }

        public int getIssuenumber() {
            return issuenumber;
        }

        @Override
        public void display() {
            String statusStr = status ? "Available" : "Checked Out";
            System.out.println("    Magazine ID: " + getId() + ", Title: " + getTitle());
            System.out.println("    Category" + getCategory() + ", Author: " + getAuthor() + ", Issue number " + issuenumber);
            System.out.println("    Status: " + statusStr);
            System.out.println("            *******************************");
        }
    }

    static class Library {
        private List<item> inventory;
        private List<LibraryMember> members;

        public Library() {
            this.inventory = new ArrayList<>();
            this.members = new ArrayList<>();
        }

        void additems(item item) {
            item.setStatus(true); // Set status to available by default
            inventory.add(item);
        }

        void checkout(int id) {
            for (item item : inventory) {
                if (item.getId() == id && item.getStatus()) {
                    item.setStatus(false); // Set status to checked out
                    System.out.println("Book " + item.getTitle() + " checked out successfully!");
                    return;
                }
            }
            System.out.println("Book with ID " + id + " not found or already checked out!");
        }

        void returnBook(int id) {
            for (item item : inventory) {
                if (item.getId() == id && !item.getStatus()) {
                    item.setStatus(true); // Set status to available
                    System.out.println("Book " + item.getTitle() + " returned successfully!");
                    return;
                }
            }
            System.out.println("Book with ID " + id + " not found or already available!");
        }

        public void registerMember(LibraryMember member) {
            members.add(member);
        }

        public List<LibraryMember> getMembers() {
            return members;
        }

        public void displaybookList() {
            System.out.println("                Library Inventory");
            for (item item : inventory) {
                item.display();
            }
        }

    }

    public static void main(String[] args) {

        Library l = new Library();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("                    Menu");
            System.out.println("        1.Add Books");
            System.out.println("        2.Add Members");
            System.out.println("        3.Book List");
            System.out.println("        4.Member List");
            System.out.println("        5.Check Out books");
            System.out.println("        6.Return Books");
            System.out.println("        7.Exit.");

            System.out.println("    Select Option");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("    1.Add Book");
                    System.out.println("    2.Add magazine");
                    System.out.println("     Enter your choice");
                    int m = sc.nextInt();
                    switch (m) {
                        case 1:
                            addbooks(l, sc);
                            break;
                        case 2:
                            addmagazine(l, sc);

                    }

                    break;
                case 2:
                    // Register a library member
                    System.out.println("    Enter details of the library member:");
                    System.out.print("      Member ID: ");
                    int memberId = sc.nextInt();
                    System.out.print("      Member Name: ");
                    String name = sc.nextLine();
                    LibraryMember member = new LibraryMember(memberId, name); // Create a new LibraryMember object
                    l.registerMember(member); // Register the member
                    System.out.println("    Member added successfully!!!");
                    break;

                case 3:
                    //Display Item List
                    l.displaybookList();
                    break;
                case 4:
                    // Display all library members
                    System.out.println("Members:");
                    for (LibraryMember m1 : l.getMembers()) {
                        int id1 = m1.getMemberId();
                        String s = m1.getName();
                        System.out.println(id1 + " " + s);
                    }
                    break;
                case 5:
                    //Checks out an item
                    System.out.println("Enter the ID of the book to check out:");
                    int checkoutId = sc.nextInt();
                    l.checkout(checkoutId);
                    break;
                case 6:
                    //Returns a book
                    System.out.println("Enter the ID of the book to return:");
                    int returnId = sc.nextInt();
                    l.returnBook(returnId);
                    break;
                case 7:
                    //Exit the program
                    System.out.println("Exiting...");
                    sc.close(); // Close the scanner
                    System.exit(0); // Exit the program
                default:
                    System.out.println("    Enter valid option");

            }
        }


    }

    private static void addbooks(Library l, Scanner sc) {


        System.out.println("    Enter Book Id:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("    Enter Book title:");
        String title = sc.nextLine();
        System.out.println("    Enter Book category:");
        String category = sc.nextLine();
        System.out.println("    Enter Author:");
        String author = sc.nextLine();
        System.out.println("    Enter ISBN:");
        String isbn = sc.nextLine();
        Lib_mngment.books book = new Lib_mngment().new books(id, title, category, author, isbn);
        l.additems(book);
        System.out.println("    Book added successfully!!!");
        System.out.println("    Press any key....");
        sc.nextLine();

    }

    private static void addmagazine(Library l, Scanner sc) {


        System.out.println("    Enter Magazine Id:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("    Enter Magazine title:");
        String title = sc.nextLine();
        System.out.println("    Enter  category:");
        String category = sc.nextLine();
        System.out.println("    Enter Author:");
        String author = sc.nextLine();
        System.out.println("    Enter Issue Number");
        int issueno = sc.nextInt();
        Lib_mngment.Magazines m = new Lib_mngment().new Magazines(id, title, category, author, issueno);
        l.additems(m);
        System.out.println("    Book added successfully!!!");
        System.out.println("    Press any key....");
        sc.nextLine();

    }
}

