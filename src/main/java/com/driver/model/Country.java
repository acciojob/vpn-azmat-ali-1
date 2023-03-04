package com.driver.model;

import javax.persistence.*;

// Note: Do not write @Enumerated annotation above CountryName in this model.
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private CountryName countryName;
    private String code;

    @OneToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
