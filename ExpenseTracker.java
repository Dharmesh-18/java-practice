import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    int amount;
    String category;
    String note;
    LocalDate date;

    public Expense(int amount, String category, String note, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }
    public Expense() {
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return  amount +" "+ category +" "+ note +" "+ date;
    }

    

    
    
}

public class ExpenseTracker {

    static Scanner sc = new Scanner(System.in);


    static Expense addExpense() {
        System.out.println("Enter the amount:");
        int amount = sc.nextInt();
        sc.nextLine(); // Consume the newline
        System.out.println("Enter the category:");
        String category = sc.nextLine();
        System.out.println("Enter a note (optional):");
        String note = sc.nextLine();
        System.out.println("Enter the date (YYYY-MM-DD):");
        String dateStr = sc.nextLine();
        LocalDate date = LocalDate.parse(dateStr);
        Expense expense = new Expense(amount, category, note, date);

        System.out.println("Expense added: " + expense);

        return expense;
    }

    static void viewExpenses(ArrayList<Expense> expenses) {
        if(expenses.isEmpty()) {
            System.out.println("No expenses to show!");
        }
        expenses.forEach(System.out::println);
    }

    static void showTotalExpenses(ArrayList<Expense> expenses) {
        int total = expenses.stream().mapToInt(Expense::getAmount).sum();
        System.out.println("Total expenses for the session: " + total);
    }

    static void saveAndExit(ArrayList<Expense> expenses) {
        System.out.println("Saving expenses...");
        try(
            FileWriter fw = new FileWriter("Expense.txt", false);
        ) {
            for(Expense e : expenses) {
                fw.append(e.toString());
                fw.append("\n");
            }

            System.out.println("Expenses added successfully");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    static ArrayList<Expense> viewExpensesFromFile(ArrayList<Expense> expenses) {

        File file = new File("Expense.txt");
        if (!file.exists()) {
            return expenses;
        }

        expenses.clear();
        try(Scanner filScanner = new Scanner(new File("Expense.txt"));)
        {
            while(filScanner.hasNextLine()) {

                String[] line = (filScanner.nextLine().split(" "));
                Expense newExpense = new Expense(Integer.parseInt(line[0]), line[1], line[2], LocalDate.parse(line[3]));
                expenses.add(newExpense);

                System.out.println(newExpense);
                
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expenses;
    }

    public static void main(String[] args) {

        ArrayList<Expense> expenses = new ArrayList<>();

        viewExpensesFromFile(expenses);
        
        int choice = 0;

        while(4 != choice) {

            System.out.println("========= Expense Tracker =========");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Show Total Expenses for the session");
            System.out.println("4. Save and Exit");
            System.out.println("5. View Expenses from file");

            System.out.println("Enter your choice:");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    Expense expense = addExpense();
                    expenses.add(expense);
                    break;
                case 2:
                    viewExpenses(expenses);
                    break;
                case 3:
                    showTotalExpenses(expenses);
                    break;
                case 4:
                    saveAndExit(expenses);
                    break;
                case 5: 
                    viewExpensesFromFile(expenses);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
}
