package edu.mum.coffee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderTable")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Orderline> orderLines = new ArrayList<>();
    @OneToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Orderline> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        int quantity = 0;
        for (Orderline ol : this.orderLines) {
            quantity += ol.getQuantity();
        }
        return quantity;
    }

    public double getTotalAmount() {
        double totalAmount = 0;

        for (Orderline ol : this.orderLines) {
            totalAmount += ol.getSubtotal();
        }
        return totalAmount;
    }

    public void addOrderLine(Orderline orderLine) {
        orderLine.setOrder(this);
        this.orderLines.add(orderLine);
    }

    public void removeOrderLine(Orderline orderLine) {
        this.orderLines.remove(orderLine);
        orderLine.setOrder(null);
    }

    public void clearOrderLines() {
        for (Orderline orderline : orderLines) {
            orderline.setOrder(null);
        }
        orderLines.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderLines=" + orderLines +
                ", person=" + person +
                '}';
    }
}
