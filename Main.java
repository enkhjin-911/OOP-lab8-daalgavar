import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Sample program - Storekeeper warehouse system
 *
 * Demonstrates receiving receipts and performing inventory counts.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. create products
        Product chips = new Product("FOOD-001", "Chips", 100);
        Product chiher = new Product("FOOD-002", "Chiher", 50);
        Product tarag = new Product("FOOD-003", "Tarag", 200);
        Product belenGoimon = new Product("FOOD-004", "Belen Goimon", 150);
        Product talh = new Product("FOOD-005", "Talh", 80);
        Product undaa = new Product("FOOD-006", "Undaa", 120);
        Product us = new Product("FOOD-007", "Us", 300);

        System.out.println("Program run date: " + LocalDate.now() + "\n");
        System.out.println("=== WAREHOUSE INITIAL BALANCE ===");
        printBalance(chips, chiher, tarag, belenGoimon, talh, undaa, us);
        System.out.println();

        // 2. create storekeeper
        String storekeeperName = "G.Enkhjin";
        Storekeeper storekeeper = new Storekeeper(storekeeperName);

        // 3. process incoming receipt
        System.out.println("\n>>> RECEIVING RECEIPT...\n");

        System.out.print("Enter supplier name: ");
        String supplier1 = scanner.nextLine();
        LocalDate receiptDate1 = LocalDate.now();
        System.out.println("Receipt date is set to: " + receiptDate1);
        Receipt receipt1 = storekeeper.createReceipt(supplier1, receiptDate1);

        int chipsQty1 = readInt(scanner, "Enter quantity for chips: ");
        receipt1.addItem(chips, chipsQty1);
        int chiherQty1 = readInt(scanner, "Enter quantity for chiher: ");
        receipt1.addItem(chiher, chiherQty1);

        // Confirm and print
        storekeeper.confirmAndPrintReceipt(receipt1);

        System.out.println("\nAfter receiving inventory balance:");
        printBalance(chips, chiher);

        // 4. second receipt
        System.out.println("\n>>> SECOND RECEIPT...\n");

        System.out.print("Enter supplier name: ");
        String supplier2 = scanner.nextLine();
        LocalDate receiptDate2 = LocalDate.now();
        System.out.println("Receipt date is set to: " + receiptDate2);
        Receipt receipt2 = storekeeper.createReceipt(supplier2, receiptDate2);

        int taragQty = readInt(scanner, "Enter quantity for tarag: ");
        receipt2.addItem(tarag, taragQty);
        int belenGoimonQty = readInt(scanner, "Enter quantity for belen goimon: ");
        receipt2.addItem(belenGoimon, belenGoimonQty);
        int chipsQty2 = readInt(scanner, "Enter quantity for chips: ");
        receipt2.addItem(chips, chipsQty2);

        storekeeper.confirmAndPrintReceipt(receipt2);

        System.out.println("\nAfter receiving inventory balance:");
        printBalance(chips, tarag, belenGoimon);

        // 5. Inventory count - chips
        System.out.println("\n\n>>> PERFORMING INVENTORY COUNT (Chips)...\n");

        int actualChips = readInt(scanner, "Enter actual quantity for chips: ");
        LocalDate countDate1 = LocalDate.now();
        System.out.println("Inventory count date is set to: " + countDate1);
        storekeeper.performInventoryCount(chips, actualChips, countDate1);

        System.out.println("\nAfter inventory count balance:");
        System.out.println(chips);

        // 6. Inventory count - chiher
        System.out.println("\n\n>>> PERFORMING INVENTORY COUNT (Chiher)...\n");

        int actualChiher = readInt(scanner, "Enter actual quantity for chiher: ");
        LocalDate countDate2 = LocalDate.now();
        System.out.println("Inventory count date is set to: " + countDate2);
        storekeeper.performInventoryCount(chiher, actualChiher, countDate2);

        System.out.println("\nAfter inventory count balance:");
        System.out.println(chiher);

        // Final status
        System.out.println("\n=== FINAL BALANCE ===");
        printBalance(chips, chiher, tarag, belenGoimon, talh, undaa, us);

        scanner.close();
    }

    private static void printBalance(Product... products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

}
