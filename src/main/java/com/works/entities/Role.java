package com.works.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority
{
    /**
     *
     */
    private static final long serialVersionUID = 9156064156119386503L;

    @Id
    private String name;

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getAuthority()
    {
        return name;
    }

}
