/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface HonorariosFacadeLocal {

    void create(Honorarios honorarios);

    void edit(Honorarios honorarios);

    void remove(Honorarios honorarios);

    Honorarios find(Object id);

    List<Honorarios> findAll();

    List<Honorarios> findRange(int[] range);

    int count();

    public List<Honorarios> findViaticosByContrato(Long contrato, String categoria);

    public boolean hayModificaciones(Long contrato, List<Honorarios> viaticos);
    
}
