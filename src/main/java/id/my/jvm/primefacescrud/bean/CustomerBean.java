package id.my.jvm.primefacescrud.bean;

import id.my.jvm.primefacescrud.entity.Customer;
import id.my.jvm.primefacescrud.service.CustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-prime-faces-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/08/26
 * Time: 10.48
 */
@Getter
@Setter
@Component
@Scope("view")
@RequiredArgsConstructor
public class CustomerBean {

    private final CustomerService customerService;

    private List<Customer> customers;

    private Customer selectedCustomer;

    private boolean editMode;

    @PostConstruct
    public void init() {
        customers = customerService.findAll();
    }

    public void openNew() {
        selectedCustomer = new Customer();
        editMode = false;
    }

    public void editCustomer(Customer customer) {
        selectedCustomer = customer;
        editMode = true;
    }

    public void saveCustomer() {
        customerService.save(selectedCustomer);
        customers = customerService.findAll();
        selectedCustomer = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer saved", null));
    }

    public void deleteCustomer(Customer customer) {
        customerService.delete(customer.getId());
        customers = customerService.findAll();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer deleted", null));
    }
}
