
package com.jsfclasses;


import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.DonutChartModel;
 
@ManagedBean(name="chartViewtypeaffectation")
public class Chartviewvehiculetypeaffectation implements Serializable {
   private DonutChartModel donutModel2;
 
    
    
    
    // prepare  lists
    List<vehiculepartypeaffectation> l;
   
    
    // get vehicule controller to get the statics list *//
     @ManagedProperty(value="#{vehiculeController}")
    private VehiculeController vehiculeController;

    public VehiculeController getVehiculeController() {
        return vehiculeController;
    }

    public void setVehiculeController(VehiculeController vehiculeController) {
        this.vehiculeController = vehiculeController;
    }
    
 
     
    
    @PostConstruct
    public void init() {
        createDonutModels();
    }
 
 
     
    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }

    
    private void createDonutModels() {
        // donut1
        donutModel2 = initDonutModel();
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(3);
        donutModel2.setShowDataLabels(true);
        donutModel2.setDataFormat("value");
        donutModel2.setShadow(true);
    
  
    
    
    }
 
    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
        Map<String,Number> circle1 = new LinkedHashMap<String, Number>();
        
        l=vehiculeController.nbvehiculetypeaffectation();
        // get elements  
        for (vehiculepartypeaffectation element : l) {

              circle1.put(element.getIdtypeaffectation().getType(),element.getNombre());
               
                      }
       
       
        
        
        // send the result ;
        model.addCircle(circle1);
        return model;
    }
  
}