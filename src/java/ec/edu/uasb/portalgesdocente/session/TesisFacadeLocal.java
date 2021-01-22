/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Tesis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface TesisFacadeLocal {

    void create(Tesis cargos);

    void edit(Tesis cargos);

    void remove(Tesis cargos);

    Tesis find(Object id);

    List<Tesis> findAll();

    List<Tesis> findRange(int[] range);

    int count();

    public List<Tesis> findByDocente(Long codDocente, Short codNivelAcad);

}
