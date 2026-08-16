package id.my.jvm.primefacescrud.repository;

import id.my.jvm.primefacescrud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-prime-faces-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/08/26
 * Time: 10.48
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
