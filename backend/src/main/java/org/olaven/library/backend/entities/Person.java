package org.olaven.library.backend.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public class Person {

    /*
     * Could also use @Embeddable, however, as Person-properties
     * are common to both author and customer, I believe @MappedSuperclass
     * is more appropriate.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String givenName;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String familyName;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getGivenName(), person.getGivenName()) &&
                Objects.equals(getFamilyName(), person.getFamilyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGivenName(), getFamilyName());
    }
}
