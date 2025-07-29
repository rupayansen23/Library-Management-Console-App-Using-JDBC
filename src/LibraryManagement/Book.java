package LibraryManagement;

public class Book {
    private String bid, bname, bauthor;
    private int currentStock, totalStock;
    public Book() {

    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Book(String bid, String bname, String bauthor, int totalStock) {
        this.bid = bid;
        this.bname = bname;
        this.bauthor = bauthor;
        this.totalStock = totalStock;
        this.currentStock = totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
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
