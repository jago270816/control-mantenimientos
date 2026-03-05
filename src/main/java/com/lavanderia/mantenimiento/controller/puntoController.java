
package com.lavanderia.mantenimiento.controller;

import com.lavanderia.mantenimiento.model.punto;
import com.lavanderia.mantenimiento.repository.puntoRepository;
import com.lavanderia.mantenimiento.service.puntoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.RequestEntity;
import java.util.List;
import java.util.Map;

import java.util.*;

@RestController
@RequestMapping("/puntos")
public class puntoController {

    private final JdbcTemplate jdbcTemplate;
    private final puntoRepository puntoRepository;
    private final puntoService puntoService;

    public puntoController(puntoRepository puntoRepository, puntoService puntoService, JdbcTemplate jdbcTemplate) {
        this.puntoRepository = puntoRepository;
        this.puntoService = puntoService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Map<String, Object>> obtenerPuntos() {

        List<punto> puntos = puntoRepository.findAll();
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (punto p : puntos) {

            Map<String, Object> datos = new HashMap<>();

            datos.put("id", p.getId());
            datos.put("nombre", p.getNombre());
            datos.put("direccion", p.getDireccion());
            datos.put("maquinas", p.getNumero_maquinas());

            datos.put("proxima_limpieza", puntoService.calcularProximaLimpieza(p));
            datos.put("estado_limpieza", puntoService.estadoLimpieza(p));
            datos.put("proximo_preventivo", puntoService.calcularProximoPreventivo(p));
            datos.put("estado_preventivo", puntoService.estadoPreventivo(p));

            resultado.add(datos);
        }

        return resultado;
    }
    
        @PostMapping("/{id}/limpieza")
        public punto registrarLimpieza(@PathVariable Long id){

        punto punto = puntoRepository.findById(id).orElseThrow();

        punto.setFecha_ultima_limpieza(java.time.LocalDate.now());

        return puntoRepository.save(punto);
        }
        
        @PostMapping("/{id}/preventivo")
        public punto registrarPreventivo(@PathVariable Long id){

        punto punto = puntoRepository.findById(id).orElseThrow();

        punto.setFecha_ultimo_preventivo(java.time.LocalDate.now());

        return puntoRepository.save(punto);
        }
        
        @PostMapping("/{id}/novedad")
        public void registrarNovedad(@PathVariable Long id, @RequestBody Map<String, String> datos){

        String descripcion = datos.get("descripcion");

        String sql = "INSERT INTO novedades (punto_id, fecha, descripcion) VALUES (?, CURDATE(), ?)";

        jdbcTemplate.update(sql, id, descripcion);
        }
        
        @GetMapping("/{id}/novedades")
        public List<Map<String, Object>> obtenerNovedades(@PathVariable Long id){

        String sql = "SELECT fecha, descripcion FROM novedades WHERE punto_id = ? ORDER BY fecha DESC";

        return jdbcTemplate.queryForList(sql, id);
        
        }
        
        @PostMapping("/{id}/repuesto")
        public void registrarRepuesto(@PathVariable Long id, @RequestBody Map<String, String> datos){

        String descripcion = datos.get("descripcion");
        String sql = "INSERT INTO repuestos (punto_id, fecha, descripcion) VALUES (?, CURDATE(), ?)";

        jdbcTemplate.update(sql, id, descripcion);
        }
        
        @PostMapping("/{id}/error")
        public void registrarError(@PathVariable Long id, @RequestBody Map<String, String> datos){

        String descripcion = datos.get("descripcion");

        String sql = "INSERT INTO errores (punto_id, fecha, descripcion) VALUES (?, CURDATE(), ?)";

        jdbcTemplate.update(sql, id, descripcion);
        }
        
        @PostMapping("/login")
        public String login(@RequestBody Map<String, String> datos) {

        String usuario = datos.get("usuario");
        String password = datos.get("password");

        System.out.println("Usuario recibido: " + usuario);
        System.out.println("Password recibido: " + password);

        String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";

        List<Map<String,Object>> resultado = jdbcTemplate.queryForList(sql, usuario, password);

        System.out.println("Filas encontradas: " + resultado.size());

        if(resultado.size() > 0){
        return "ok";
        }else{
        return "error";
            }

        }
        

}