/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface CandidatoDocenteFacadeLocal {

    void create(CandidatoDocente candidatoDocente);

    void edit(CandidatoDocente candidatoDocente);

    void remove(CandidatoDocente candidatoDocente);

    CandidatoDocente find(Object id);

    List<CandidatoDocente> findAll();

    List<CandidatoDocente> findRange(int[] range);

    int count();

    public List<CandidatoDocente> getCandidatosDocentes();

    public CandidatoDocente getCandidato(Long prfCodigo);
    
}
