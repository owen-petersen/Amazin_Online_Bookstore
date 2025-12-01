package net.azurewebsites.amazin_online_bookstore.entity;

import jakarta.persistence.*;

@Entity
public class JaccardDistance {
    
    // Variables
    
    @Id
    @GeneratedValue
    private Integer id;

    // Note: person1 id must be less than person 2 id when persisted
    @ManyToOne(fetch = FetchType.EAGER)
    private Person person1;
    @ManyToOne(fetch = FetchType.EAGER)
    private Person person2;
    private double jaccardIndex;

    // Constructors

    public JaccardDistance() {}

    public JaccardDistance(Person person1, Person person2) {
        // Need person1 id to be less than person2 id in database
        if (person1.getId() < person2.getId()) {
            this.person1 = person1;
            this.person2 = person2;
        } else {
            this.person1 = person2;
            this.person2 = person1;
        }
    }

    public JaccardDistance(Person person1, Person person2, double jaccardIndex) {
        this(person1, person2);
        this.jaccardIndex = jaccardIndex;
    }

    // Getters and Setters

    public Integer getId() { return id; }

    public Person getPerson1() { return person1; }
    public boolean setPerson1(Person person1) {
        if (person1.getId() >= this.person2.getId()) {
            return false;
        }
        this.person1 = person1;
        return true;
    }

    public Person getPerson2() { return person2; }
    public boolean setPerson2(Person person2) {
        if (person2.getId() <= this.person1.getId()) {
            return false;
        }
        this.person2 = person2;
        return true;
    }

    public double getJaccardIndex() { return jaccardIndex; }
    public void setJaccardIndex(double jaccardIndex) { this.jaccardIndex = jaccardIndex; }

}
