/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author phung
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.File;

public class Sendemail {
    private String toEmail;
    private String subject;
    private String message;
    private File attachment;

    public Sendemail(String toEmail, String subject, String message, File attachment) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
    }

    public void send() throws Exception {
        // Cấu hình SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Xác thực tài khoản email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thanhtvph51876@gmail.com", "owjrxyqdsjgipxda");
            }
        });

        // Tạo email
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("thanhtvph51876@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject(subject);

        // Tạo nội dung email
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(message);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);

        // Thêm tệp đính kèm
        if (attachment != null) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(attachment);
            multipart.addBodyPart(attachmentPart);
        }

        msg.setContent(multipart);

        // Gửi email
        Transport.send(msg);
        System.out.println("Email đã được gửi thành công!");
    }
}

