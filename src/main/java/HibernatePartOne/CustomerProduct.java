package HibernatePartOne;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_product")
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_product_id", nullable = false)
    private int customerProductId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "value")
    private int value;

    public CustomerProduct(){}

    public int getBuy_id() {
        return customerProductId;
    }
    public int getValue() {
        return value;
    }

    public void setBuy_id(int buy_id) {
        this.customerProductId = buy_id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
