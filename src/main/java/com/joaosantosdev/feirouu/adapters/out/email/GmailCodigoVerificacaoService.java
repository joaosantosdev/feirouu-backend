package com.joaosantosdev.feirouu.adapters.out.email;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnvioCodigoVerificacaoServicePort;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class GmailCodigoVerificacaoService implements EnvioCodigoVerificacaoServicePort {


    @Override
    public Boolean enviar(CodigoVerificacao codigoVerificacao) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("feirouudev@gmail.com", "tcc12345678");
                    }
                });

        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("feirouudev@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(codigoVerificacao.getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Feirouu - Confirmação de email");

            message.setText("Código de verificação: " + codigoVerificacao.getCodigo());
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            return false;
        }
    }
}
