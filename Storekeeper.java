import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CRC Card: Storekeeper
 *
 * Responsibilities:
 *  - Create, confirm, and print receipts
 *  - Perform inventory counts
 *  - Manage products
 *
 * Collaborates:
 *  - Receipt       (creates receipts)
 *  - InventoryCount (performs counts)
 *  - Product       (monitors products)
 */
public class Storekeeper {

    private String name;                          // Storekeeper name
    private List<Receipt> receipts;               // List of receipts
    private List<InventoryCount> inventoryCounts; // List of inventory counts

    public Storekeeper(String name) {
        this.name            = name;
        this.receipts        = new ArrayList<>();
        this.inventoryCounts = new ArrayList<>();
    }

    /**
     * Create a new incoming receipt
     *
     * @param deliveredBy  Name of the person delivering
     * @param receiptDate  Date received
     * @return             New receipt (ready for adding items)
     */
    public Receipt createReceipt(String deliveredBy, LocalDate receiptDate) {
        Receipt receipt = new Receipt(receiptDate, deliveredBy);
        receipts.add(receipt);
        return receipt;
    }

    /**
     * Confirm receipt and add items to inventory
     * Then print the receipt.
     *
     * @param receipt  Receipt to confirm
     */
    public void confirmAndPrintReceipt(Receipt receipt) {
        receipt.applyToInventory();
        receipt.print();
    }

    /**
     * Perform inventory count for one product
     *
     * @param product        Product to count
     * @param actualQuantity Actual counted quantity
     * @param countDate      Count date
     * @return               Inventory count report (ready to print)
     */
    public InventoryCount performInventoryCount(Product product,
                                                int actualQuantity,
                                                LocalDate countDate) {
        InventoryCount count = new InventoryCount(countDate, product, actualQuantity);
        inventoryCounts.add(count);
        count.print();
        return count;
    }

    // --- Getters ---

    public String getName()                          { return name; }
    public List<Receipt> getReceipts()               { return List.copyOf(receipts); }
    public List<InventoryCount> getInventoryCounts() { return List.copyOf(inventoryCounts); }
}
