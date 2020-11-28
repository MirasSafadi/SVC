package models;

public class VisitCardDTO {
    private int id;
    private String owner;
    private String full_name;
    private String email;
    private String phone_number;
    private String job;
    private String company;
    private String address;

    public VisitCardDTO(int id,String owner, String full_name,String email,String phone_number,String job,String company,String address){
        this.setId(id);
        this.setOwner(owner);
        this.setFull_name(full_name);
        this.setEmail(email);
        this.setPhone_number(phone_number);
        this.setJob(job);
        this.setCompany(company);
        this.setAddress(address);
    }



    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
    public String getFull_name() {
        return full_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getJob() {
        return job;
    }
    public String getCompany() {
        return company;
    }
    public String getAddress() {
        return address;
    }

    //=======================================================================================

    public void setId(int id) {
        this.id = id;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s %s",this.id,this.owner,this.email,this.full_name);
    }
}
