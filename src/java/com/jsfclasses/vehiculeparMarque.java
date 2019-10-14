
package com.jsfclasses;

import com.entities.Marque;


public class vehiculeparMarque {
    private Long Nombre;
    private Marque idmarque;

    public vehiculeparMarque(Long Nombre, Marque idmarque) {
        this.Nombre = Nombre;
        this.idmarque = idmarque;
    }

    public Long getNombre() {
        return Nombre;
    }

 
    public void setNombre(Long Nombre) {
        this.Nombre = Nombre;
    }

    public Marque getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(Marque idmarque) {
        this.idmarque = idmarque;
    }

 
    
    
    
}

