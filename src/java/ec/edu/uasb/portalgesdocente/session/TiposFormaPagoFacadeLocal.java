/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.TiposFormaPago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface TiposFormaPagoFacadeLocal {

    void create(TiposFormaPago tiposFormaPago);

    void edit(TiposFormaPago tiposFormaPago);

    void remove(TiposFormaPago tiposFormaPago);

    TiposFormaPago find(Object id);

    List<TiposFormaPago> findAll();

    List<TiposFormaPago> findRange(int[] range);

    int count();
    
}
