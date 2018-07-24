/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CafeDataPackage.service;

import CafeDataPackage.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Nijes
 */
@Stateless
@Path("item")
public class ItemFacadeREST extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "ProjectCafePU")
    private EntityManager em;

    public ItemFacadeREST() {
        super(Item.class);
    }

    @POST
    @Consumes({"application/json"})
    public String createItem(Item entity) {
        System.out.println("Posting item");
        super.create(entity);
        em.flush();
       return entity.getIditem().toString();
    }


    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

   

     @GET
    @Produces({"application/json"})//all the items by particular order number
    public List<Item> findAll(@QueryParam("ordernumber") int OrderID) {
        return em.createNamedQuery("Item.findByOrdernumber", Item.class).setParameter("ordernumber", new Integer(OrderID)).getResultList();
    }

    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
