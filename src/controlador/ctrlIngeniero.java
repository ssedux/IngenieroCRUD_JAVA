
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlIngeniero;
import vistas.JfrIngeniero; 

//1- Implementar herencia: ActionListener
public class ctrlIngeniero implements MouseListener, KeyListener {
      //2- Parametros
    private modelo.mdlIngeniero modelo;
    private vistas.JfrIngeniero vista;
    //clase
    public ctrlIngeniero(modelo.mdlIngeniero modelo, vistas.JfrIngeniero vista) {
        this.modelo = modelo;
        this.vista = vista;
        //elementos
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbIngeniero.addMouseListener(this);
        
        modelo.Mostrar(vista.tbIngeniero);
}
    //Eventos
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////////////4- Detección de clicks en la vista
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty()||vista.txtPeso.getText().isEmpty()||vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombreIngeniero(vista.txtNombre.getText());
                    modelo.setEdadIngeniero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPesoIngeniero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreoIngeniero(vista.txtCorreo.getText());
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbIngeniero);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty()||vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbIngeniero);
                modelo.Mostrar(vista.tbIngeniero);
                modelo.limpiar(vista);
            }
        }
        
         if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty()||vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombreIngeniero(vista.txtNombre.getText());
                    modelo.setEdadIngeniero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPesoIngeniero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreoIngeniero(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.tbIngeniero);
                    modelo.Mostrar(vista.tbIngeniero);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
     if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbIngeniero  ) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbIngeniero, vista.txtBuscar);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
  
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    }

    
}
        
