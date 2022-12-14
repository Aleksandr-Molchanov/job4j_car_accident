package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "accident_type_id", foreignKey = @ForeignKey(name = "ACCIDENT_TYPE_ID_FK"))
    private AccidentType accidentType;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "accident_rule", joinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "rule_id", nullable = false, updatable = false)})
    private Set<Rule> rules;

    public Accident() {
    }

    public Accident(int id, String name, String text, String address, AccidentType accidentType, Set<Rule> rules) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.accidentType = accidentType;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && Objects.equals(name, accident.name)
                && Objects.equals(text, accident.text)
                && Objects.equals(address, accident.address)
                && Objects.equals(accidentType, accident.accidentType)
                && Objects.equals(rules, accident.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address, accidentType, rules);
    }

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", address='" + address + '\''
                + ", accidentType=" + accidentType
                + ", rules=" + rules
                + '}';
    }
}