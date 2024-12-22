package com.logisticplus.formulario_correo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api/mail")
@CrossOrigin("*")
@Slf4j
public class MailController {

    @Autowired
    public JavaMailSender mailSender;

    @PostMapping("/send")
    public String sendMail(@RequestParam String name, @RequestParam String apellido,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fechaNacimiento)
    {
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-DD");
            String fechaFormateada=dateFormat.format(fechaNacimiento);
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo("naruto_011990@hotmail.com");
            mailMessage.setSubject("Nuevo Formulario");
            mailMessage.setText("Nombre: "+ name + "\nApellido: " +apellido+ "\nFecha: "+ fechaFormateada);
            mailMessage.setFrom("jsalaza201@gmail.com");
            mailSender.send(mailMessage);
            log.info("Correo enviado exitosamente");
            return "Correo enviado correctamente";
        }catch (Exception e) {
            log.error("Correo no enviado");

            return "Error al enviar el mensaje";
        }
    }


}
