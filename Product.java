/**
 * CRC Card: Product
 *
 * Responsibilities:
 *  - Keep product information (name, code, quantity)
 *  - Track inventory quantity
 *  - Update via inventory count
 *
 * Collaborates:
 *  - ReceiptItem (added by receipts)
 *  - InventoryCount (updated by inventory counts)
 */
public class Product {

    private String productCode;   // Product code
    private String productName;   // Product name
    private int quantity;         // Inventory quantity

    public Product(String productCode, String productName, int initialQuantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = initialQuantity;
    }

    /**
     * Add quantity from incoming receipt
     */
    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity to add must be greater than zero.");
        }
        this.quantity += amount;
    }

    /**
     * Update inventory by count result
     * Calculates difference from expected quantity.
     */
    public int updateByCount(int actualQuantity) {
        int difference = actualQuantity - this.quantity; // + = surplus, - = shortage
        this.quantity = actualQuantity;
        return difference;
    }

    // --- Getters ---

    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public int getQuantity()       { return quantity; }

    @Override
    public String toString() {
        return String.format("[%s] %s — balance: %d pcs", productCode, productName, quantity);
    }
}
