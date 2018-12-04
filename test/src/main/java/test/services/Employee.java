package test.services;

public abstract class Employee {
    private String name ;
    private int no;
    private String school_name;

    public Employee(String name, int no, String school_name){
        this.name = name;
        this.no = no;
        this.school_name = school_name;
    }

    public String getName()
    {
        return name;
    }


}
