
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vistas.JfrIngeniero;

/**
 *
 * @author chris
 */
public class mdlIngeniero {
    //parametros
     
    private String nombreIngeniero; 
    private int edadIngeniero;        
    private int pesoIngeniero;    
    private String correoIngeniero;  
    
    //Metodos get y set

    public String getNombreIngeniero() {
        return nombreIngeniero;
    }

    public void setNombreIngeniero(String nombreIngeniero) {
        this.nombreIngeniero = nombreIngeniero;
    }

    public int getEdadIngeniero() {
        return edadIngeniero;
    }

    public void setEdadIngeniero(int edadIngeniero) {
        this.edadIngeniero = edadIngeniero;
    }

    public int getPesoIngeniero() {
        return pesoIngeniero;
    }

    public void setPesoIngeniero(int pesoIngeniero) {
        this.pesoIngeniero = pesoIngeniero;
    }

    public String getCorreoIngeniero() {
        return correoIngeniero;
    }

    public void setCorreoIngeniero(String correoIngeniero) {
        this.correoIngeniero = correoIngeniero;
    }
    
    //Métodos
    public void Guardar(){
       Connection conexion = ClaseConexion.getConexion(); 
       try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbIngeniero(UUID_Ingeniero, Nombre_Ingeniero, Edad_Ingeniero, Peso_Ingeniero,Correo_Ingeniero) VALUES (?, ?, ?, ?,?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
          pstmt.setString(1,UUID.randomUUID().toString());
          pstmt.setString(2,getNombreIngeniero());
          pstmt.setInt(3,getEdadIngeniero());
          pstmt.setInt(4,getPesoIngeniero());
          pstmt.setString(5,getCorreoIngeniero());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }   
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Ingeniero", "Nombre_Ingeniero", "Edad_Ingeniero", "Peso_Ingeniero,Correo_Ingeniero"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbIngeniero";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Ingeniero"), 
                    rs.getString("Nombre_Ingeniero"), 
                    rs.getInt("Edad_Ingeniero"),
                    rs.getDouble("Peso_Ingeniero"),
                    rs.getString("Correo_Ingeniero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbIngeniero where UUID_Ingeniero = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbIngeniero set Nombre_Ingeniero= ?, Edad_Ingeniero = ?, Peso_Ingeniero = ?,Correo_Ingeniero=? where UUID_Ingeniero = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombreIngeniero());
                updateUser.setInt(2, getEdadIngeniero());
                updateUser.setInt(3, getPesoIngeniero());
                updateUser.setString(4, getCorreoIngeniero());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Ingeniero", "Nombre_Ingeniero", "Edad_Ingeniero", "Peso_Ingeniero","Correo_Ingeniero"});
        try {
            String sql = "SELECT * FROM tbIngeniero WHERE Nombre_Ingeniero LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Ingeniero"), rs.getString("Nombre_Ingeniero"), rs.getInt("Edad_Ingeniero"),rs.getInt("Peso_Ingeniero"), rs.getString("Correo_Ingeniero")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
    public void limpiar(JfrIngeniero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    public void cargarDatosTabla(JfrIngeniero vista) {
        
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbIngeniero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_Ingeniero = vista.tbIngeniero.getValueAt(filaSeleccionada, 0).toString();
            String Nombre_Ingeniero = vista.tbIngeniero.getValueAt(filaSeleccionada, 1).toString();
            String Edad_Ingeniero = vista.tbIngeniero.getValueAt(filaSeleccionada, 2).toString();
            String Peso_Ingeniero = vista.tbIngeniero.getValueAt(filaSeleccionada, 3).toString();
            String Correo_Ingeniero = vista.tbIngeniero.getValueAt(filaSeleccionada, 3).toString();
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(Nombre_Ingeniero);
            vista.txtEdad.setText(Edad_Ingeniero);
            vista.txtPeso.setText(Peso_Ingeniero);
            vista.txtCorreo.setText(Correo_Ingeniero);
        }
    }
}

