/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Rubros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface RubrosFacadeLocal {

    void create(Rubros rubros);

    void edit(Rubros rubros);

    void remove(Rubros rubros);

    Rubros find(Object id);

    List<Rubros> findAll();

    List<Rubros> findRange(int[] range);

    int count();

    public List<Rubros> getRubrosByCateg(String categ);
    
}
