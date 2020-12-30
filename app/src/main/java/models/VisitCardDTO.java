package models;

import Utils.utils;

public class VisitCardDTO {
    private int id;
    private String owner;
    private String email;
    private String prefix;
    private String first_name;
    private String middle_name;
    private String last_name;
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
        this.prefix = builder.prefix;
        this.first_name = builder.first_name;
        this.middle_name = builder.middle_name;
        this.last_name = builder.last_name;
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
        private String prefix;
        private String first_name;
        private String middle_name;
        private String last_name;
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
        public Builder setOwner(String owner) throws IllegalArgumentException {
            if(owner.isEmpty()) throw new IllegalArgumentException("Visit Card must have owner");
            this.owner = owner;
            return this;
        }
        public Builder setEmail(String email) throws IllegalArgumentException {
            if(email.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.email = email;
            return this;
        }
        public Builder setPrefix(String prefix){
            this.prefix = prefix;
            return this;
        }
        public Builder setFirst_name(String first_name) throws IllegalArgumentException {
            if(first_name.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.first_name = first_name;
            return this;
        }
        public Builder setMiddle_name(String middle_name){
            this.middle_name = middle_name;
            return this;
        }
        public Builder setLast_name(String last_name) throws IllegalArgumentException {
            if(last_name.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.last_name = last_name;
            return this;
        }
        public Builder setPosition_title(String position_title) throws IllegalArgumentException{
            if(position_title.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.position_title = position_title;
            return this;
        }
        public Builder setCompany(String company) throws IllegalArgumentException{
            if(company.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.company = company;
            return this;
        }

        public Builder setAddress(String address) throws IllegalArgumentException {
            if(address.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
            this.address = address;
            return this;
        }

        public Builder setTelephone(String telephone) throws IllegalArgumentException {
            if(telephone.isEmpty()) throw new IllegalArgumentException("This field is mandatory");
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
        public VisitCardDTO build() {
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
    public String getPrefix() { return prefix; }
    public String getFirst_name() {
        return first_name;
    }
    public String getMiddle_name() { return middle_name; }
    public String getLast_name() { return last_name; }
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
        return String.format("%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",this.id,this.owner,this.email,this.prefix,this.first_name,this.middle_name,this.last_name,this.position_title,this.company,this.address,this.telephone,this.fax,this.mobile,this.website);
    }
    public String prepareForCompression(){
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",this.email,this.prefix,this.first_name,this.middle_name,this.last_name,this.position_title,this.company,this.address,this.telephone,this.fax,this.mobile,this.website);
    }


    public static VisitCardDTO stringToVisitCard(String enc){
        String[] info = enc.split(";");
        //fill empty fields in the end with empty strings
        info = utils.fillArray(info);
        return new Builder().
                            setId(Integer.parseInt(info[0])).
                            setOwner(info[1]).
                            setEmail(info[2]).
                            setPrefix(info[3]).
                            setFirst_name(info[4]).
                            setMiddle_name(info[5]).
                            setLast_name(info[6]).
                            setPosition_title(info[7]).
                            setCompany(info[8]).
                            setAddress(info[9]).
                            setTelephone(info[10]).
                            setFax(info[11]).
                            setMobile(info[12]).
                            setWebsite(info[13]).
                            build();
    }
    public static VisitCardDTO receiveVisitCard(String enc){
        String[] info = enc.split(";");
        //fill empty fields in the end with empty strings
        info = utils.fillArray(info);
        return new Builder().
                setEmail(info[2]).
                setPrefix(info[3]).
                setFirst_name(info[4]).
                setMiddle_name(info[5]).
                setLast_name(info[6]).
                setPosition_title(info[7]).
                setCompany(info[8]).
                setAddress(info[9]).
                setTelephone(info[10]).
                setFax(info[11]).
                setMobile(info[12]).
                setWebsite(info[13]).
                build();
    }
}
