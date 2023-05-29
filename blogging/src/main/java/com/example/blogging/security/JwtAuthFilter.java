package com.example.blogging.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken=request.getHeader("Authoraization");

        //bearer 2352523sdgsg
        System.out.println(requestToken);

        String userName=null;
        String token=null;

        if (request!=null && requestToken.startsWith("Bearer ")){
             token= requestToken.substring(7);

             try {

                 userName=this.jwtTokenHelper.getUserNameFromToken(token);
             }
             catch (IllegalArgumentException e){
                 System.out.println("unable to get jwt token");
             }
             catch (ExpiredJwtException e){
                 System.out.println("jwt token expired");
             }
             catch (MalformedJwtException e){
                 System.out.println("invalid jwt");
             }
        }
        else {
            System.out.println("JWT token does not begin with bearer");
        }


        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails1=this.userDetailsService.loadUserByUsername(userName);
            if (this.jwtTokenHelper.validateToken(token,null)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails1,null,userDetails1.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                System.out.println("invalid token");
            }
        }
        else {
            System.out.println("username  is null or context is null");
        }

        filterChain.doFilter(request,response) ;

    }


}

