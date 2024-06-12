package com.inventory.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inventory.service.AdminUserDetailService;
import com.inventory.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@CrossOrigin
public class JwtAuthFilter extends OncePerRequestFilter {

 @Autowired
  private JwtService jwtService;

  @Autowired
private AdminUserDetailService adminUserDetailService;


@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                String authHeader=request.getHeader("Authorization");
                System.out.println(request.getAuthType() + " authHeader");
                System.out.println(request.getHeader("Authorization") + " auth ");
                log.info("authHeader Request --> {} ",authHeader);
                String token="";
                String email=null;
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    token=authHeader.substring(7);
                    email=jwtService.extractUsername(token);

                }

                if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                      
                       UserDetails userDetails= adminUserDetailService.loadUserByUsername(email);

                       if(jwtService.validateToken(token, userDetails)){

                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                       }
                }
                filterChain.doFilter(request, response);

    }

}
