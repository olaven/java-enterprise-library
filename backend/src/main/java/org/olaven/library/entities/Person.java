package org.olaven.library.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class Person {

    /*
     * Person could also be extended instead
     * of embedded, however, I wanted to showcase
     * @Embeddable
     */

    @NotBlank
    @Size(min = 2, max = 100)
    private String givenName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String familyName;

    public Person() {
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
