
package com.cice.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servicio extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("Estoy llamando al servicio...");
        System.out.println("Modelo: " + req.getParameter("modelo"));
        System.out.println("Modelo: " + req.getParameter("color"));
        System.out.println("Modelo: " + req.getParameter("potencia"));
    
        String usuario= "root";
        String pass= "root";
        String driver= "com.mysql.jdbc.Driver";
        String host="jdbc:mysql://localhost:8889/test";
        String modelo= req.getParameter("modelo");
        String color= req.getParameter("color");
        String potencia= req.getParameter("potencia");
        
        
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(host, usuario, pass);
            String SQL = "INSERT INTO tablacoches (modelo, color, potencia) VALUES ( '"+modelo+"', '"+color+"', '"+potencia+"')";
            System.out.println(SQL);
            Statement st = con.createStatement();
                        
            st.execute(SQL);
            
            String select = "SELECT * FROM tablacoches";
            
            ResultSet resultado = st.executeQuery(select);
            
            while(resultado.next()){
                System.out.println("Modelo: "+ resultado.getString("color"));
            }
            resultado.close();
            st.close();
            con.close();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        resp.sendRedirect("./index.jsp");
}
}