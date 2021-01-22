/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PorTutoriaMonografiaFacadeLocal {

    void create(PorTutoriaMonografia porTutoriaMonografia);

    void edit(PorTutoriaMonografia porTutoriaMonografia);

    void remove(PorTutoriaMonografia porTutoriaMonografia);

    PorTutoriaMonografia find(Object id);

    List<PorTutoriaMonografia> findAll();

    List<PorTutoriaMonografia> findRange(int[] range);

    int count();

    public List<PorTutoriaMonografia> getTutoriasContrato(ContratoDocente contrato);

}
