package models;

public class VisitCardDTO {
    private int id;
    private String owner;
    private String email;
    private String full_name;
    private String position_title;
    private String company;
    private String address;
    private String telephone;
    private String fax;
    private String mobile;
    private String website;

    private VisitCardDTO(Builder builder){
        this.id = builder.id;
        this.owner = builder.owner;
        this.email = builder.email;
        this.full_name = builder.full_name;
        this.position_title = builder.position_title;
        this.company = builder.company;
        this.address = builder.address;
        this.telephone = builder.telephone;
        this.fax = builder.fax;
        this.mobile = builder.mobile;
        this.website = builder.website;
    }

    public static class Builder{
        private int id;
        private String owner;
        private String email;
        private String full_name;
        private String position_title;
        private String company;
        private String address;
        private String telephone;
        private String fax;
        private String mobile;
        private String website;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setFull_name(String full_name) {
            this.full_name = full_name;
            return this;
        }
        public Builder setPosition_title(String position_title) {
            this.position_title = position_title;
            return this;
        }
        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }
        public Builder setFax(String fax) {
            this.fax = fax;
            return this;
        }
        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }
        public Builder setWebsite(String website) {
            this.website = website;
            return this;
        }
        //===========================================
        public VisitCardDTO build() throws IllegalArgumentException{
            if(email == null) this.email = "";
            if(full_name == null) throw new IllegalArgumentException("This field is mandatory");
            if(position_title == null) throw new IllegalArgumentException("This field is mandatory");
            if(company == null) throw new IllegalArgumentException("This field is mandatory");
            if(address == null) this.address = "";
            if(telephone == null) this.telephone = "";
            if(fax == null) this.fax = "";
            if(mobile == null) this.mobile = "";
            if(website == null) this.website = "";
            return new VisitCardDTO(this);
        }
    }
    //=======================================================================================
    public int getId() {
        return id;
    }
    public String getOwner() {
        return owner;
    }
    public String getEmail() { return email; }
    public String getFull_name() {
        return full_name;
    }
    public String getPosition_title() { return position_title; }
    public String getCompany() { return company; }
    public String getAddress() { return address; }
    public String getTelephone() {
        return telephone;
    }
    public String getFax() { return fax; }
    public String getMobile() { return mobile; }
    public String getWebsite() { return website; }


    //=======================================================================================



    @Override
    public String toString() {
        return String.format("%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",this.id,this.owner,this.email,this.full_name,this.position_title,this.company,this.address,this.telephone,this.fax,this.mobile,this.website);
    }

    public static VisitCardDTO stringToVisitCard(String enc){
        String[] info = enc.split(";");
        return new Builder().
                             setId(Integer.parseInt(info[0])).
                             setOwner(info[1]).
                             setEmail(info[2]).
                             setFull_name(info[3]).
                             setPosition_title(info[4]).
                             setCompany(info[5]).
                             setAddress(info[6]).
                             setTelephone(info[7]).
                             setFax(info[8]).
                             setMobile(info[9]).
                             setWebsite(info[10]).
                             build();
    }
}
