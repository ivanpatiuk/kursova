package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

// Клас працівник
public class Worker extends Employee{
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double salary;

    public Worker() {
    }

    public Worker(String name, String surname, String sitePassword, Double salary) {
        super(name, surname, sitePassword);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return Objects.equals(salary, worker.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary);
    }
}
//************************************************

