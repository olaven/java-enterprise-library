package org.olaven.library.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@NoArgsConstructor
@Data
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

}
