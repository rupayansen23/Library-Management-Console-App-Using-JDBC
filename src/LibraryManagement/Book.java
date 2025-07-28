package LibraryManagement;

public class Book {
    private String bid, bname, bauthor;
    private int currentStock, totalStock;

    public Book(String bid, String bname, String bauthor, int totalStock) {
        this.bid = bid;
        this.bname = bname;
        this.bauthor = bauthor;
        this.totalStock = totalStock;
        this.currentStock = totalStock;
    }

    public String getBid() {
        return bid;
    }

    public String getBname() {
        return bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getTotalStock() {
        return totalStock;
    }
}
