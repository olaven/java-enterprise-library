package org.olaven.library.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Data
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

}
