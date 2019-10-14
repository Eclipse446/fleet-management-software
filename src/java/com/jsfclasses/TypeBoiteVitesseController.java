package com.jsfclasses;

import com.entities.TypeBoiteVitesse;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.TypeBoiteVitesseFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "typeBoiteVitesseController")
@SessionScoped
public class TypeBoiteVitesseController implements Serializable {

    private TypeBoiteVitesse current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.TypeBoiteVitesseFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TypeBoiteVitesseController() {
    }

    public TypeBoiteVitesse getSelected() {
        if (current == null) {
            current = new TypeBoiteVitesse();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TypeBoiteVitesseFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List_typeboitevitesse";
    }

    public String prepareView() {
        current = (TypeBoiteVitesse) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TypeBoiteVitesse();
        selectedItemIndex = -1;
        return "Create_typeboitevitesse";
    }

    public String create() {
        try {
            current.setId(new Integer(1));
            getFacade().create(current);
            JsfUtil.addSuccessMessage("crée");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String prepareEdit() {
        current = (TypeBoiteVitesse) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit_typeboitevitesse";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("modifié");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String destroy() {
        current = (TypeBoiteVitesse) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return "List_typeboitevitesse";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = TypeBoiteVitesse.class)
    public static class TypeBoiteVitesseControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TypeBoiteVitesseController controller = (TypeBoiteVitesseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "typeBoiteVitesseController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TypeBoiteVitesse) {
                TypeBoiteVitesse o = (TypeBoiteVitesse) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TypeBoiteVitesseController.class.getName());
            }
        }
    }
}
