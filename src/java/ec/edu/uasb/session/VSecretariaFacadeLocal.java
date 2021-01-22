/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.VSecretaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface VSecretariaFacadeLocal {

    void create(VSecretaria vSecretaria);

    void edit(VSecretaria vSecretaria);

    void remove(VSecretaria vSecretaria);

    VSecretaria find(Object id);

    List<VSecretaria> findAll();

    List<VSecretaria> findRange(int[] range);

    int count();


}
