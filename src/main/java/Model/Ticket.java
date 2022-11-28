package Model;

public class Ticket {
    private int id;
    private double amount;

    private String employeeName;

    private String description;

    private String status;



    public Ticket(int id, String employeeName, double amount, String description, String status){
        this.employeeName = employeeName;
        this.amount = amount;
        this.id = id;
        this.status = "Pending";
    }

    public Ticket() {

    }



    public String getEmployeeName(){return employeeName;}
    public double getAmount(){
        return amount;
    }
    public int getId(){return id;}
    public String getDescription() {return description;}
    public void setStatus(String status) {this.status = status;}

    public void setAmount(double amount) {this.amount = amount;}

    public void setEmployeeName(String employeeName) {this.employeeName = employeeName;}

    public void setDescription(String description) {this.description = description;}
}
