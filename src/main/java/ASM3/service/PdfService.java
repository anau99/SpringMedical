package ASM3.service;

import ASM3.entity.Patient;
import ASM3.entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public static byte[] createPdfFromPatient(Patient patient, User user) {
        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Thêm nội dung vào tệp PDF từ đối tượng Patient
            Paragraph paragraph = new Paragraph("Thông tin bệnh nhân:");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(new Paragraph("Tên bệnh nhân: " + user.getName()));
            document.add(new Paragraph("Giới tính: " + user.getGender()));
            document.add(new Paragraph("Địa chỉ: " + user.getAddress()));
            document.add(new Paragraph("Tình trạng: " + patient.getStatus().getName()));
            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
