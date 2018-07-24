
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CafeDataPackage.service;

import CafeDataPackage.Detail;
import CafeDataPackage.Invoice;
import CafeDataPackage.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
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
@Path("invoice")
public class InvoiceFacadeREST extends AbstractFacade<Invoice> {
    @PersistenceContext(unitName = "ProjectCafePU")
    private EntityManager em;

    public InvoiceFacadeREST() {
        super(Invoice.class);
    }

    @POST
    @Consumes({"application/json"})
    public String createInvoice(Invoice entity) {
        super.create(entity);
        em.flush();
        return entity.getIdorder().toString();
    }

   
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public String edit(@PathParam("id") Integer id, Invoice entity) {
        System.out.println("Putting item");
        super.edit(entity);
       return entity.getIdorder().toString();
    }
    

    

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
