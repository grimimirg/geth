/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author agrimandi
 */
@Entity
@Table(name = "stuff", uniqueConstraints =
{
    @UniqueConstraint(columnNames = "id")
})
public class Stuff
{

    private long id;
    private String string;
    private int number;

    public Stuff()
    {
    }

    public Stuff(String string, int number)
    {
        this.string = string;
        this.number = number;
    }

    public Stuff(long id, String string, int number)
    {
        this.id = id;
        this.string = string;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Column(name = "string", unique = false, nullable = true, length = 100)
    public String getString()
    {
        return string;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    @Column(name = "number", unique = false, nullable = true)
    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

}
