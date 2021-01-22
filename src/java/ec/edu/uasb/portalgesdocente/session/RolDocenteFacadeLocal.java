/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface RolDocenteFacadeLocal {

    void create(RolDocente rolDocente);

    void edit(RolDocente rolDocente);

    void remove(RolDocente rolDocente);

    RolDocente find(Object id);

    List<RolDocente> findAll();

    List<RolDocente> findRange(int[] range);

    int count();

    public List<RolDocente> getRolDocenteByEstadoAndNivel(String estado, Short codNivelAcad);

}
