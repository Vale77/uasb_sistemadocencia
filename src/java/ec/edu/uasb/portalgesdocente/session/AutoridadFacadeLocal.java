/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Autoridad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AutoridadFacadeLocal {

    void create(Autoridad autoridad);

    void edit(Autoridad autoridad);

    void remove(Autoridad autoridad);

    Autoridad find(Object id);

    List<Autoridad> findAll();

    List<Autoridad> findRange(int[] range);

    int count();
    
}
