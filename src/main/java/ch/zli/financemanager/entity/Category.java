package ch.zli.financemanager.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Category")
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Payment> payments;

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setCategory(this);
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setCategory(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
