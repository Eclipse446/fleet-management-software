
package com.jsfclasses;



import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.DonutChartModel;
 
@ManagedBean(name="chartView")
public class ChartView implements Serializable {
 
    
    private DonutChartModel donutModel2;
 
    
    
    
    // prepare  lists
    List<depstat> l;
   
    
    // get depense controller to get the statics list *//
    @ManagedProperty(value="#{depenseController}")
    private DepenseController depenseController;
    

    
   
    // getter setter depense controller
    public DepenseController getDepenseController() {
        return depenseController;
    }

    public void setDepenseController(DepenseController depenseController) {
        this.depenseController = depenseController;
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
        
        l=depenseController.getdeplist();
        // get elements  
        for (depstat element : l) {

              circle1.put(element.getIdrubrique().getLibelle(),element.getMontant());
               
                      }
       
       
        
        
        // send the result ;
        model.addCircle(circle1);
        return model;
    }
}