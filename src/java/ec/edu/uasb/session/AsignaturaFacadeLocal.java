/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.Asignatura;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AsignaturaFacadeLocal {

    void create(Asignatura materia);

    void edit(Asignatura materia);

    void remove(Asignatura materia);

    Asignatura find(Object id);

    List<Asignatura> findAll();

    List<Asignatura> findRange(int[] range);

    int count();

    public List<Asignatura> getAsignaturasByAreaProg(Long codArea, Long codProg, Long anio);

    public Asignatura getAsignatura(ContratoDocente contrato);

}
