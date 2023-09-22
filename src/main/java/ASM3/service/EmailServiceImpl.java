package ASM3.service;

import ASM3.entity.Patient;
import ASM3.entity.User;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public boolean sendConfirmationEmail(List<User> userList, Patient patient) {
        // Các thông tin SMTP và tài khoản email của bạn
        String host = "smtp.gmail.com"; // SMTP của Gmail
        String port = "587";
        String username = userList.get(0).getEmail();
        String password = "cajhgirhajvlpjcj"; // Mật khẩu ứng dụng

        // Cấu hình JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Tạo phiên làm việc với email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userList.get(1).getEmail()));
            message.setSubject("Dear " + "mr/ms " + userList.get(1).getName());

            // Tạo phần nội dung của email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Xin chào,\n\nDưới đây là thông tin của bệnh nhân.");

            // Đính kèm tệp PDF
            MimeBodyPart attachmentPart = new MimeBodyPart();
            byte[] pdfData = PdfService.createPdfFromPatient(patient, userList.get(1));
            attachmentPart.setContent(pdfData, "application/pdf");
            attachmentPart.setFileName("patient_info.pdf");

            // Tạo multipart để kết hợp phần nội dung và phần đính kèm
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Đặt nội dung email
            message.setContent(multipart);

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
