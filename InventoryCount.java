import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CRC Card: InventoryCount
 *
 * Responsibilities:
 *  - Record count date
 *  - Record actual quantity for one product
 *  - Compute surplus/shortage versus expected quantity
 *  - Update product inventory with actual quantity
 *  - Print inventory count report
 *
 * Collaborates:
 *  - Product (check and update)
 *  - Storekeeper (performs counts)
 */
public class InventoryCount {

    private static int counter = 1;

    private String countNumber;         // Inventory count number
    private LocalDate countDate;        // Count date
    private Product product;            // Counted product
    private int expectedQuantity;       // Expected quantity
    private int actualQuantity;         // Actual quantity
    private int difference;            // Difference (+surplus, -shortage)

    /**
     * Performs inventory count and updates product quantity.
     *
     * @param countDate      Count date
     * @param product        Product being counted
     * @param actualQuantity Actual counted quantity
     */
    public InventoryCount(LocalDate countDate, Product product, int actualQuantity) {
        if (actualQuantity < 0) {
            throw new IllegalArgumentException("Actual quantity cannot be negative.");
        }
        this.countNumber      = String.format("IC-%04d", counter++);
        this.countDate        = countDate;
        this.product          = product;
        this.expectedQuantity = product.getQuantity(); //Quantity before count
        this.actualQuantity   = actualQuantity;
        this.difference       = product.updateByCount(actualQuantity); // Update and calculate difference
    }

    /**
     * Print inventory count report
     */
    public void print() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String line = "=".repeat(50);
        System.out.println(line);
        System.out.println("           INVENTORY COUNT REPORT");
        System.out.println(line);
        System.out.printf("Number       : %s%n", countNumber);
        System.out.printf("Date         : %s%n", countDate.format(fmt));
        System.out.printf("Product      : %s%n", product.getProductName());
        System.out.printf("Product code : %s%n", product.getProductCode());
        System.out.println("-".repeat(50));
        System.out.printf("Expected     : %d pcs%n", expectedQuantity);
        System.out.printf("Actual       : %d pcs%n", actualQuantity);
        System.out.printf("Difference   : %+d pcs  (%s)%n",
                difference,
                difference > 0 ? "SURPLUS" : difference < 0 ? "SHORTAGE" : "NO DIFFERENCE");
        System.out.println(line);
    }

    // --- Getters ---

    public String getCountNumber()    { return countNumber; }
    public LocalDate getCountDate()   { return countDate; }
    public Product getProduct()       { return product; }
    public int getExpectedQuantity()  { return expectedQuantity; }
    public int getActualQuantity()    { return actualQuantity; }
    public int getDifference()        { return difference; }
}
