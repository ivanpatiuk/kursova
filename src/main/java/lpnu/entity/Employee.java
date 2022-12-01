package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lpnu.entity.mapper.Convertable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

// Абстрактний клас співробітник, який успадковує бухгалтер та працівник
public abstract class Employee implements Convertable {
    @JsonIgnore
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String sitePassword;

    public Employee() {
    }

    public Employee(String name, String surname, String sitePassword) {
        this.name = name;
        this.surname = surname;
        this.sitePassword = sitePassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSitePassword() {
        return sitePassword;
    }

    public void setSitePassword(String sitePassword) {
        this.sitePassword = sitePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(sitePassword, employee.sitePassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, sitePassword);
    }
}
//************************************************

