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
import java.util.Map;

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

    private static final List<String> SERIES_OPTIONS = List.of(
            "Naruto", "Jujutsu Kaisen", "Demon Slayer", "One Piece", "Doraemon");

    private static final Map<String, String> SERIES_SEVERITIES = Map.of(
            "Naruto", "warning",
            "Jujutsu Kaisen", "danger",
            "Demon Slayer", "success",
            "One Piece", "info",
            "Doraemon", "contrast");

    private final CustomerService customerService;

    private List<Customer> customers;

    private Customer selectedCustomer;

    private boolean editMode;

    @PostConstruct
    public void init() {
        customers = customerService.findAll();
    }

    public List<String> getSeriesOptions() {
        return SERIES_OPTIONS;
    }

    public String seriesSeverity(String series) {
        return SERIES_SEVERITIES.getOrDefault(series, "secondary");
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

    public void prepareDelete(Customer customer) {
        selectedCustomer = customer;
    }

    public void deleteConfirmed() {
        customerService.delete(selectedCustomer.getId());
        customers = customerService.findAll();
        selectedCustomer = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer deleted", null));
    }
}
