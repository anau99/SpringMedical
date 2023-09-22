package ASM3.controller;
import ASM3.config.JwtUtil;
import ASM3.form.AddADoctorUserForm;
import ASM3.form.BlockForm;
import ASM3.form.UserRegisterForm;
import ASM3.service.DoctorService;
import ASM3.service.ScheduleService;
import ASM3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = {"/apiAdmin"},
        produces = {"application/json;charset=UTF-8"}
)
public class AdminUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleService scheduleService;

    /*Hàm này sử dụng cho user và doctor để block hoặc unblock tài khoản
    *@params String token : dùng để xác định userID và RoleID
    *
     */
    @PutMapping("/unblockOrBlockUser")
    public ResponseEntity<?> unblockOrBlockUser(@RequestHeader("Authorization") String token,
                                                         @RequestBody BlockForm blockForm){
        String[]split = token.split(" ");
        if(token == null || token.isEmpty() ||  split==null || split.length!=2 || blockForm.getDecide()>1 ||blockForm.getDecide()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
        }
        String roleId = JwtUtil.getRoleFromToken(split[1]);
        if(roleId.toCharArray()[0]!='1')
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");

        try {

            userService.unBlockOrBlockAUser(blockForm);
            //Trong trường hợp block là bác sỹ thì xóa userId này ra khỏa doctorUser
            if(blockForm.getRoleID()==3 && blockForm.getDecide()==0){
                doctorService.deleteDoctorUser(blockForm);
            }


            if(blockForm.getDecide()==0 && blockForm.isOK()){
                return new ResponseEntity<>("Đã block User thành công ", HttpStatus.OK);
            }
            else if(blockForm.getDecide()==1 && blockForm.isOK())
                return new ResponseEntity<>("Đã unblock User thành công ", HttpStatus.OK);
            return new ResponseEntity<>("Đã unblock/block User không thành công ", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi hệ thống");
        }


    }

    @PostMapping("/AddADoctorUser")
    public ResponseEntity<?> addADoctor(@RequestHeader("Authorization") String token,
                                                @RequestBody AddADoctorUserForm addADoctorUserForm){
        String[]split = token.split(" ");
        if(token == null || token.isEmpty() ||  split==null || split.length!=2 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
        }
        String roleId = JwtUtil.getRoleFromToken(split[1]);
        if(roleId.toCharArray()[0]!='1')
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");

        if(addADoctorUserForm.getGetRoleId()!=3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chỉ thêm tài khoản là BS ");
        //Check format Email
        if(!userService.checkFormatEmail(addADoctorUserForm.getEmail().trim()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Email không đúng định dạng");

        if(!userService.checkPassword(addADoctorUserForm.getPassword(),addADoctorUserForm.getRePassword()))
            return ResponseEntity.badRequest().body("Mã lỗi 400: Mật khẩu và xác nhận mật khẩu không khớp");

        if(!userService.checkEmail(addADoctorUserForm.getEmail()))
            return ResponseEntity.badRequest().body("Mã lỗi 400: Email đã tồn tại");
        try {
            this.userService.register((UserRegisterForm) addADoctorUserForm);
            //Thêm vào 1 doctor trong doctorUser
            doctorService.creatADoctorUser(addADoctorUserForm);
            return new ResponseEntity("Đăng ký thành công", HttpStatus.OK);
        } catch (Exception var7) {
            var7.printStackTrace();
            return new ResponseEntity("Mã lỗi 500: Lỗi khi đăng ký", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/ViewScheduleOfAUser")
    public ResponseEntity<?>getScheduleOfUser(@RequestParam("query") String query,@RequestHeader("Authorization") String token){
        try {
            String[]split = token.split(" ");
            if(token == null || token.isEmpty() ||  split==null || split.length!=2){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
            }
            String roleId = JwtUtil.getRoleFromToken(split[1]);
            if(roleId.toCharArray()[0]!='1')
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");
            return ResponseEntity.ok(scheduleService.getHistoryOfUserId(Integer.parseInt(query)));

        }catch (Exception e){
            return new ResponseEntity("Mã lỗi 500: Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ViewScheduleOfADoctorUser")
    public ResponseEntity<?>getScheduleOfADoctor(@RequestParam("query") String query,@RequestHeader("Authorization") String token){
        try {
            String[]split = token.split(" ");
            if(token == null || token.isEmpty() ||  split==null || split.length!=2){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Sai cú pháp ");
            }
            String roleId = JwtUtil.getRoleFromToken(split[1]);
            if(roleId.toCharArray()[0]!='1')
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã lỗi 401: Bạn không có quyền truy cập ");
            return ResponseEntity.ok(scheduleService.getListScheduleDoctor(Integer.parseInt(query)));

        }catch (Exception e){
            return new ResponseEntity("Mã lỗi 500: Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
