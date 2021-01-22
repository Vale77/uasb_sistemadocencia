/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.AnioAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AnioAcademicoFacadeLocal {

    void create(AnioAcademico anioAcademico);

    void edit(AnioAcademico anioAcademico);

    void remove(AnioAcademico anioAcademico);

    AnioAcademico find(Object id);

    List<AnioAcademico> findAll();

    List<AnioAcademico> findRange(int[] range);

    int count();

}
