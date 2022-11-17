package Model;

public class Ticket {
    private int id;
    private double amount;
    private String type;
    private String employeeName;

    private String status;
    public Ticket(String employeeName,String type ,double amount,String status){
        this.employeeName = employeeName;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public String getType(){
        return this.type;
    }

    public String getEmployeeName(){return this.employeeName;}

    public double getAmount(){
        return this.amount;
    }
}
