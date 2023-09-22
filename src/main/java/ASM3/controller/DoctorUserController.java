package ASM3.controller;
import ASM3.config.JwtUtil;
import ASM3.controller.response.ResponseProfileUser;
import ASM3.entity.Patient;
import ASM3.entity.Schedule;
import ASM3.entity.User;
import ASM3.form.CancelAcceptedScheduleForm;
import ASM3.form.ForgetForm;
import ASM3.form.SendingEmailForm;
import ASM3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping(
        value = {"/apiDoctorUser"},
        produces = {"application/json;charset=UTF-8"}
)
public class DoctorUserController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    /*
    *@params String token : Nhận token từ client
    *Output: trả về danh sách bệnh nhân của bác sỹ đó
     */
    @GetMapping("/getListPatients")
    public ResponseEntity<?> getListPatients(@RequestHeader("Authorization") String token){
        try {
            //Lấy thông tin user sử dụng idUser trong token mà User đã đăng nhập
            String[]split = token.split(" ");
            if(token == null || token.isEmpty() ||  split==null || split.length!=2){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
            }
            String roleId = JwtUtil.getRoleFromToken(split[1]);
            if(roleId.toCharArray()[0]!='3')
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");

            return ResponseEntity.ok(patientService.getListPatients(Integer.parseInt(JwtUtil.getUserIdFromToken(split[1]))));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy thông tin");
        }

    }

    /*Trong thuộc tính cancel cua Schedule sẽ có 3 trường hợp : -0 - Mới tạo, 1 - Cancel, 2 - Accepted
    *@Params Token : Dùng để kiểm tra thuộc role nào
    * @Params cancelAcceptedScheduleForm: input data
     */
    @PutMapping("/canceledOrAcceptedASchedule")
    public ResponseEntity<?> canceledOrAcceptedASchedule(@RequestHeader("Authorization") String token,
                                                         @RequestBody CancelAcceptedScheduleForm cancelAcceptedScheduleForm){
        String[]split = token.split(" ");
        if(token == null || token.isEmpty() ||  split==null || split.length!=2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
        }
        String roleId = JwtUtil.getRoleFromToken(split[1]);
        if(roleId.toCharArray()[0]!='3')
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");

        //Muốn hủy 1 schedule
        if(cancelAcceptedScheduleForm.getAcceptedOrCancel()==1){
            if(!doctorService.cancelASchedule(JwtUtil.getUserIdFromToken(split[1]),cancelAcceptedScheduleForm))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Không thể hủy schedule này ");
            else
                return ResponseEntity.ok("Đã hủy lịch khám này thành công");
        }
        //Accepted
        else if(cancelAcceptedScheduleForm.getAcceptedOrCancel()==2){
            if(!doctorService.appceptedASchedule(JwtUtil.getUserIdFromToken(split[1]),cancelAcceptedScheduleForm))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Không thể nhận schedule này ");
            else{
                User user = new User();
                User doctorUser = new User();
                scheduleService.getInfor(cancelAcceptedScheduleForm.getIdSchedule(),user,doctorUser);
                patientService.createAPatient(user,doctorUser);
                return ResponseEntity.ok("Đã nhận lịch khám này thành công");
            }

        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nhập không đúng cú pháp");

    }

    /*Gửi email cho 1 bệnh nhân kèm theo file
     *@Params Token : Dùng để kiểm tra thuộc role nào
     * @Params sendingEmailForm: Id của bênh nhân để gửi email
     */

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmailToPatient(@RequestHeader("Authorization") String token,
                                                @RequestBody SendingEmailForm sendingEmailForm){
        try {
            String[]split = token.split(" ");
            if(token == null || token.isEmpty() ||  split==null || split.length!=2){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
            }
            String roleId = JwtUtil.getRoleFromToken(split[1]);
            if(roleId.toCharArray()[0]!='3')
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");
            //Lấy địa chỉ email của bac sy va bệnh nhân
            List<User>users = userService.getEmailDoctorAndPatient(Integer.parseInt(JwtUtil.getUserIdFromToken(split[1])),
                    sendingEmailForm.getUserPatientId());
            if(users.isEmpty()||users.size()<2)
                return new ResponseEntity("Mã lỗi 500: Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);
            //Lấy thông tin của bệnh nhân để gửi email kèm pdf
            Patient patient =  patientService.getApatientByUserId(sendingEmailForm.getUserPatientId());
           boolean isOK = emailService.sendConfirmationEmail(users,patient);
           if(!isOK)
               return ResponseEntity.ok("Gửi mail không thành công");

            return ResponseEntity.ok("Đã gửi mail thành công");

        }catch (Exception e){
            return new ResponseEntity("Mã lỗi 500: Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
