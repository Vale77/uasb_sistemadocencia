/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.DatosViajero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface DatosViajeroFacadeLocal {

    void create(DatosViajero datosViajero);

    void edit(DatosViajero datosViajero);

    void remove(DatosViajero datosViajero);

    DatosViajero find(Object id);

    List<DatosViajero> findAll();

    List<DatosViajero> findRange(int[] range);

    int count();
    
}
