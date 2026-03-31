import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * CRC Card: Receipt
 *
 * Responsibilities:
 *  - Store receipt number and date
 *  - Store deliveredBy name
 *  - Manage receipt line items (ReceiptItem)
 *  - Apply items to inventory (applyToInventory)
 *  - Generate print format for receipt
 *
 * Collaborates:
 *  - ReceiptItem (line items)
 *  - Storekeeper (actor)
 */
public class Receipt {

    private static int counter = 1;

    private String receiptNumber;          // Receipt number
    private LocalDate receiptDate;         // Received date
    private String deliveredBy;            // Delivered by
    private List<ReceiptItem> items;       // Items
    private boolean applied;              // Is applied to inventory

    public Receipt(LocalDate receiptDate, String deliveredBy) {
        this.receiptNumber = String.format("ОП-%04d", counter++);
        this.receiptDate   = receiptDate;
        this.deliveredBy   = deliveredBy;
        this.items         = new ArrayList<>();
        this.applied       = false;
    }

    /**
     * Add an item to the receipt
     */
    public void addItem(Product product, int quantity) {
        if (applied) {
            throw new IllegalStateException("Cannot add items to an already confirmed receipt.");
        }
        items.add(new ReceiptItem(product, quantity));
    }

    /**
     * Confirm the receipt and add items to inventory
     */
    public void applyToInventory() {
        if (applied) {
            throw new IllegalStateException("Receipt is already confirmed.");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Receipt is empty.");
        }
        for (ReceiptItem item : items) {
            item.applyToProduct();
        }
        applied = true;
    }

    /**
     * Print the receipt in a readable format
     */
    public void print() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String line = "=".repeat(50);
        System.out.println(line);
        System.out.println("          RECEIPT");
        System.out.println(line);
        System.out.printf("Number    : %s%n", receiptNumber);
        System.out.printf("Date      : %s%n", receiptDate.format(fmt));
        System.out.printf("Delivered : %s%n", deliveredBy);
        System.out.println("-".repeat(50));
        System.out.printf("%-22s | %s%n", "Product", "Qty");
        System.out.println("-".repeat(50));
        for (ReceiptItem item : items) {
            System.out.println(item);
        }
        System.out.println(line);
        System.out.printf("Total items: %d%n", items.size());
        System.out.println(line);
    }

    // --- Getters ---

    public String getReceiptNumber()   { return receiptNumber; }
    public LocalDate getReceiptDate()  { return receiptDate; }
    public String getDeliveredBy()     { return deliveredBy; }
    public List<ReceiptItem> getItems(){ return List.copyOf(items); }
    public boolean isApplied()         { return applied; }
}
