package org.olaven.library.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Person {

    /*
     * Could also use @Embeddable, however, as Person-properties
     * are common to both author and customer, I believe @MappedSuperclass
     * is more appropriate.
     */

    @Id
    @GeneratedValue
    protected long id;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String givenName;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String familyName;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
