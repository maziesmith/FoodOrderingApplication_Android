/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CafeDataPackage.service;

import CafeDataPackage.Detail;
import CafeDataPackage.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Nijes
 */
@Stateless
@Path("detail")
public class DetailFacadeREST extends AbstractFacade<Detail> {
    @PersistenceContext(unitName = "ProjectCafePU")
    private EntityManager em;

    public DetailFacadeREST() {
        super(Detail.class);
    }


    @GET
    @Produces({"application/json"})//all the items by particular order number
    public List<Detail> findAll(@QueryParam("ordernumber") int OrderID) {
        return em.createNamedQuery("Detail.findByOrdernumber", Detail.class).setParameter("ordernumber", String.valueOf(OrderID)).getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
