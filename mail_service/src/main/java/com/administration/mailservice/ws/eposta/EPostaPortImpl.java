package com.administration.mailservice.ws.eposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

@WebService(
      serviceName = "EPostaService",
      portName = "EPostaPort",
      targetNamespace = "http://administracija/ws/eposta",
      endpointInterface = "com.administration.mailservice.ws.eposta.EPosta")
@Service
public class EPostaPortImpl implements EPosta {

    private static final Logger LOG = Logger.getLogger(EPostaPortImpl.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String subjekt, String tekst, String primalac) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(primalac);
        email.setSubject(subjekt);
        email.setText(tekst);
        mailSender.send(email);
    }

    @Override
    public void sendMailMultipart(String subjekt, String tekst, String primalac, byte[] prilog) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(primalac));
                mimeMessage.setFrom(new InternetAddress("cultoffer@gmail.com"));
                mimeMessage.setSubject(subjekt);
                mimeMessage.setText(tekst);

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("prilog.pdf",
                        new InputStreamResource(new ByteArrayInputStream(prilog)));
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
