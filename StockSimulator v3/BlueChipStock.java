public class BlueChipStock extends Stock {
    public BlueChipStock(String label, String ownedBy, double price, int shares, double ratio) {
        super(label, ownedBy, price, shares, ratio);
    }
    public BlueChipStock(String label, String ownedBy, double price, int shares, double dividend, double ratio) {
        super(label, ownedBy, price, shares, dividend, ratio);
    }
}