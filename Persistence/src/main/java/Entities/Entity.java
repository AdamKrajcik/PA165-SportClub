package Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jan Cech
 * Abstract class that represents database entity
 */
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
}
