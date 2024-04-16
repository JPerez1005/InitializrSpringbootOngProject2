package com.system.ong.security.jwt;

import com.system.ong.security.CustomerDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author perez
 */

@Component
public class JwtFilter extends OncePerRequestFilter{

    //implementamos la clase jwtUtil para tener sus metodos
    @Autowired
    private JwtUtil jwtUtil;
    
    //tambien esta es como una clase de UserDetailService
    //obtenemos todos los datos del usuario
    @Autowired
    private CustomerDetailsService CustomerDetailsService;
    
    /*Definimos el username nulo y los reclamos nulos tambien*/
    Claims claims = null;
    private String username=null;

    /*Es el nucleo de la funcionalidad de un filtro y contiene los siguientes parametros
    */
    @Override
    protected void doFilterInternal
        /*
        HttpServletRequest request: proporciona acceso a la solicitud HTTP entrante,
        se usa para leer informacion de esa solicitud.
                
        HttpServletResponse response: proporciona acceso a la respuesta HTTP de la solicitud,
        con eso yo manipulo la respuesta que se le envia al cliente
                
        FilterChain: cadena de filtros, Permite que tu filtro pase el control de la
        solicitud al siguiente filtro en la cadena o al servlet final que manejará
        la solicitud si no hay más filtros.
        */
        (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            //si la ruta de la solicitud coincide con las siguientes rutas
            //request.getServletPath(): obtenemos la ruta
            //matches: comparamos las rutas
            if(request.getServletPath().matches("/persona/ingresar|/persona/agregar_persona")){
                /*doFilter: es el método que realmente pasa la solicitud y la respuesta al
                siguiente filtro en la cadena (o al servlet final) para que pueda ser procesado.*/
                filterChain.doFilter(request, response);
            }else{
                /*está obteniendo el valor del encabezado de autorización, que debería contener el token JWT.*/
                String authorizationHeader = request.getHeader("Authorization");
                String token=null;

                if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                    /*Extraemos el token que antes era nulo y le metemmos el token jwt
                    del encabezado empezando por la linea 7 para extraer eltoken real
                    sin el subfijo bearer*/
                    token=authorizationHeader.substring(7);

                    /*mandamos esos datos al jwtUtil para llenar los datos del
                    username y el reclamo completo*/
                    username=jwtUtil.extractUsername(token);
                    claims=jwtUtil.extractAllClaims(token);
                }

                /*Ademas de pedir el nombre del usurio y verificar si es nulo,
                se hace una proceso de verificacion de autenticacion en el
                contexto de seguridad, verifica si está autenticado*/
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    /*SecurityContextHolder: es una clase proporcionada por Spring Security que
                    almacena los detalles de autenticación del usuario actualmente autenticado
                    
                    getAuthentication():  devuelve el objeto de autenticación actual.
                    Si este objeto es nulo, significa que el usuario
                    no está actualmente autenticado en el sistema.*/
                    
                    /*Ahora obtenemos los datos en userDetails y los autenticamos*/
                    UserDetails userDetails=CustomerDetailsService.loadUserByUsername(username);
                    
                    /*Verificamos si el token es valido*/
                    if(jwtUtil.validateToken(token, userDetails)){
                        /*UserDetails contiene la informacion del usuario autenticado
                        
                        el segundo parametro es null que representa la contraseña del usuario,
                        pero como ya validamos el token no necesitamos una contraseña en este punto
                        
                        userDetails.getAuthorities():  Se pasan las autoridades del usuario.
                        Estas autoridades indican los roles o privilegios del usuario autenticado.*/
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                                /*Con esto estamos autenticando el usuario*/
                                new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

                        /*Este objeto proporciona información adicional sobre la autenticación que puede
                        ser útil para los registros o auditorías, pero no se está usando ahorita mismo*/
                        new WebAuthenticationDetailsSource().buildDetails(request/*usamos la solicitud*/);
                        
                        /*SecurityContextHolder: es una clase proporcionada por Spring Security que
                        almacena los detalles de autenticación del usuario actualmente autenticado

                        getAuthentication():  devuelve el objeto de autenticación actual.
                        Si este objeto es nulo, significa que el usuario
                        no está actualmente autenticado en el sistema.*/
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        /*Es decir estamos mandando la nueva autenticacion con usernamePasswordAuthenticationToken*/
                    }
                }
                /*doFilter: es el método que realmente pasa la solicitud y la respuesta al
                siguiente filtro en la cadena (o al servlet final) para que pueda ser procesado.*/
                filterChain.doFilter(request, response);
                //En resumen es un filtrado de datos muy necesario
            }
    }
    
    public Boolean isAdmin(){
        System.out.println(claims);
        return "administrador".equalsIgnoreCase((String) claims.get("role"));
    }
    
    public Boolean isDirector(){
        return "director".equalsIgnoreCase((String) claims.get("role"));
    }
    
    public Boolean isAuxiliar(){
        return "auxiliar".equalsIgnoreCase((String) claims.get("role"));
    }
    
    public String getCurrentPersona(){
        return username;
    }
    
}
