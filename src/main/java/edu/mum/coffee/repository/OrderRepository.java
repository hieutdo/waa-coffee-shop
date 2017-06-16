package edu.mum.coffee.repository;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findDistinctOrderByOrderLines_Product(Product product);

    List<Order> findOrderByPerson(Person person);

    List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);


}
