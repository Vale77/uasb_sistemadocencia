/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.DatosViajero;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface ContratoDocenteFacadeLocal {

    void create(ContratoDocente contratoDocente);

    void edit(ContratoDocente contratoDocente);

    void remove(ContratoDocente contratoDocente);

    ContratoDocente find(Object id);

    List<ContratoDocente> findAll();

    List<ContratoDocente> findRange(int[] range);

    int count();

    public void create(ContratoDocente entity, List<Honorarios> viaticos, List<PorTutoriaMonografia> tutorias, DatosViajero datViajero, DatosViajero residencia);

    public void edit(ContratoDocente entity, List<Honorarios> viaticos, List<PorTutoriaMonografia> tutorias, DatosViajero datViajeroEditado, DatosViajero residencia);

    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format) throws SQLException;

    public List<ContratoDocente> findByEstado(String est, String estPerfil, String estAprob, Date desde, Date hasta, Long nroSolic, String tipoListado);

    public List<ContratoDocente> findByAreaAndProg(Long area, Long prog, Long anio);

    public void createContrato(ContratoDocente entity, EstadoSolicContrato esc);

    public boolean getOkNroContrato(String nroContrato);

    public String getMensaje(ContratoDocente contratoDocente);

    public List<ContratoDocente> findAprobadosByEstado(String est, Date desde);

    public List<ContratoDocente> findBecasInvestByArea(Long area, Long anio);



}
