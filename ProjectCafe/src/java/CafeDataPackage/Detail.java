/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CafeDataPackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nijes
 */
@Entity
@Table(name = "detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detail.findAll", query = "SELECT d FROM Detail d"),
    @NamedQuery(name = "Detail.findByIditem", query = "SELECT d FROM Detail d WHERE d.iditem = :iditem"),
    @NamedQuery(name = "Detail.findByName", query = "SELECT d FROM Detail d WHERE d.name = :name"),
    @NamedQuery(name = "Detail.findByOrdernumber", query = "SELECT d FROM Detail d WHERE d.ordernumber = :ordernumber")})
public class Detail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iditem")
    private int iditem;
    @Size(max = 64)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "ordernumber")
    private String ordernumber;

    public Detail() {
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
    
}
