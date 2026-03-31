/**
 * CRC Card: ReceiptItem
 *
 * Responsibilities:
 *  - Store details for one receipt line item
 *  - Store quantity received
 *  - Reference product
 *
 * Collaborates:
 *  - Product (product details)
 *  - Receipt (owning receipt)
 */
public class ReceiptItem {

    private Product product;   // Product
    private int quantity;      // Received quantity

    public ReceiptItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Add quantity to product when receipt is confirmed
     */
    public void applyToProduct() {
        product.addQuantity(quantity);
    }

    public Product getProduct()  { return product; }
    public int getQuantity()     { return quantity; }

    @Override
    public String toString() {
        return String.format("  %-20s | %d pcs", product.getProductName(), quantity);
    }
}
