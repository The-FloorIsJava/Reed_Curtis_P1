package Model;

public class Ticket {
    private int id;
    private double amount;
    private String type;
    private String employeeName;
    private String status;



    public Ticket(String employeeName,String type ,double amount,String status,int id){
        this.employeeName = employeeName;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.id = id;
    }
    public String getStatus(){
        return status;
    }

    public String getType(){
        return type;
    }

    public String getEmployeeName(){return employeeName;}

    public double getAmount(){
        return amount;
    }

    public int getId(){return id;}
}
