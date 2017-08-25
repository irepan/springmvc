package com.alcatrazstudios.springmvc.domain;

import javax.persistence.*;

/**
 * Created by irepan on 10/07/17.
 */
@Entity
public class Customer implements DomainObject{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    Integer version;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

/*    @Embedded
    private Address billingAddress;

    @Embedded
    private Address shippingAddress;*/

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "addressLine1",
                    column = @Column( name = "billing_address_line1" )
            ),
            @AttributeOverride(
                    name = "addressLine2",
                    column = @Column( name = "billing_address_line2" )
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column( name = "billing_city" )
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column( name = "billing_state" )
            ),
            @AttributeOverride(
                    name = "zipCode",
                    column = @Column( name = "billing_zip_code" )
            )
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "addressLine1",
                    column = @Column( name = "shipping_address_line1" )
            ),
            @AttributeOverride(
                    name = "addressLine2",
                    column = @Column( name = "shipping_address_line2" )
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column( name = "shipping_city" )
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column( name = "shipping_state" )
            ),
            @AttributeOverride(
                    name = "zipCode",
                    column = @Column( name = "shipping_zip_code" )
            )
    })
    private Address shippingAddress;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNo) {
        this.phoneNumber = phoneNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
