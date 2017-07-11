package com.example.demo.Vista;

import com.example.demo.Per.Estudiante;
import com.example.demo.Per.EstudianteRepositorio;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.soap.Text;

/**
 * Created by alumno on 10/07/2017.
 */
@SpringUI
public class Principal extends UI {

    @Autowired
    EstudianteRepositorio repositorio;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Button("Hola"));

        Grid<Estudiante> grid = new Grid<>();
        grid.addColumn(Estudiante::getId).setCaption("Id");
        grid.addColumn(Estudiante::getNombre).setCaption("Nombre");
        grid.addColumn(Estudiante::getEdad).setCaption("Edad");
grid.setItems(repositorio.findAll());

        TextField nombre = new TextField("Nombre");
        TextField edad = new TextField("edad");

        Button button = new Button("agregar");

       button.addClickListener(new Button.ClickListener() {
           @Override
           public void buttonClick(Button.ClickEvent clickEvent) {
               Estudiante estudiante=new Estudiante();
               estudiante.setNombre(nombre.getValue());
               estudiante.setEdad(Integer.parseInt( edad.getValue()));
               repositorio.save(estudiante);

               grid.setItems(repositorio.findAll());

               nombre.clear();
               edad.clear();
           }
       });
       verticalLayout.addComponents(nombre,edad,button,grid);

        setContent(verticalLayout);


    }
}
