package org.quarkus.samples.petclinic.owner;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.FormParam;

import org.quarkus.samples.petclinic.model.Person;
import org.quarkus.samples.petclinic.visit.Visit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

	@Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;


    @Override
    public String toString() {
        return "Owner [email=" + email + "]";
    }
    public String getPassword() {
    return this.password;
    }


}
