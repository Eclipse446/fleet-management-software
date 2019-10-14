
package com.jsfclasses;

import com.entities.TypeAffectation;


public class vehiculepartypeaffectation {
    private Long Nombre;
    private TypeAffectation idtypeaffectation;

    public vehiculepartypeaffectation(Long Nombre, TypeAffectation idtypeaffectation) {
        this.Nombre = Nombre;
        this.idtypeaffectation = idtypeaffectation;
    }

  
    

    public Long getNombre() {
        return Nombre;
    }

 
    public void setNombre(Long Nombre) {
        this.Nombre = Nombre;
    }

    public TypeAffectation getIdtypeaffectation() {
        return idtypeaffectation;
    }

    public void setIdtypeaffectation(TypeAffectation idtypeaffectation) {
        this.idtypeaffectation = idtypeaffectation;
    }

  
    
    
    
}

