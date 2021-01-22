/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface TipoEstadoFacadeLocal {

    void create(TipoEstado tipoEstado);

    void edit(TipoEstado tipoEstado);

    void remove(TipoEstado tipoEstado);

    TipoEstado find(Object id);

    List<TipoEstado> findAll();

    List<TipoEstado> findRange(int[] range);

    int count();

    public String getEmailSigProcesoByEstado(String estado);

}
