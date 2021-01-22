/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface EstadoSolicContratoFacadeLocal {

    void create(EstadoSolicContrato estadoSolicContrato);

    void edit(EstadoSolicContrato estadoSolicContrato);

    void remove(EstadoSolicContrato estadoSolicContrato);

    EstadoSolicContrato find(Object id);

    List<EstadoSolicContrato> findAll();

    List<EstadoSolicContrato> findRange(int[] range);

    int count();

    public EstadoSolicContrato getUltimoEstadoContrato(Long nroSolic);

    public void create(EstadoSolicContrato entity, List<Honorarios> viaticos);

    public List<EstadoSolicContrato> getHistoricoContrato(Long nroSolic);

}
