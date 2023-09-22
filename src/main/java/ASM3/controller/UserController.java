package ASM3.controller;
import ASM3.config.JwtUtil;
import ASM3.controller.response.GeneralSearch;
import ASM3.controller.response.ResponseProfileUser;
import ASM3.controller.response.ResponseToken;
import ASM3.controller.response.SearchDoctorBySpecialization;
import ASM3.entity.*;
import ASM3.form.ForgetForm;
import ASM3.form.LoginForm;
import ASM3.form.ScheduleForm;
import ASM3.form.UserRegisterForm;
import ASM3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(
        value = {"/api"},
        produces = {"application/json;charset=UTF-8"}
)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public UserController() {
    }

    /*@params UserRegisterForm userRegisterForm: dữ liệu lấy từ client
    *Output: trả về API tương ứng
     */
    @PostMapping({"/register"})
    public ResponseEntity<String> register(@RequestBody UserRegisterForm userRegisterForm) {
        boolean isOK = this.roleService.checkRoleId(userRegisterForm.getGetRoleId());
        if (!isOK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Đăng ký phải nhập 1 trong 3 Roles : " +
                    "1,2,3 tương ứng lần lược với Admin, user, doctor");
        } else {
            boolean isOk2 = this.userService.checkFormatEmail(userRegisterForm.getEmail().trim());
            if (!isOk2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Email không đúng định dạng");
            } else {
                boolean isOk3 = this.userService.checkPassword(userRegisterForm.getPassword(), userRegisterForm.getRePassword());
                if (!isOk3) {
                    return ResponseEntity.badRequest().body("Mã lỗi 400: Mật khẩu và xác nhận mật khẩu không khớp");
                } else {
                    boolean isOk4 = this.userService.checkEmail(userRegisterForm.getEmail());
                    if (!isOk4) {
                        return ResponseEntity.badRequest().body("Mã lỗi 400: Email đã tồn tại");
                    } else {
                        try {
                            this.userService.register(userRegisterForm);
                            return new ResponseEntity("Đăng ký thành công", HttpStatus.OK);
                        } catch (Exception var7) {
                            var7.printStackTrace();
                            return new ResponseEntity("Mã lỗi 500: Lỗi khi đăng ký", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                }
            }
        }
    }

    /*@params LoginForm loginForm: dữ liệu lấy từ client để login
     *Output: trả về TOKEN
     */

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        StringBuilder sb = new StringBuilder();
        if (this.userService.checkFormatEmail(loginForm.getEmail().trim()) && this.userService.isValidLogin(loginForm, sb)) {
            String[] split = sb.toString().split("_");
            String token = JwtUtil.generateToken(split[0], split[1]);
            ResponseToken responseToken = new ResponseToken();
            responseToken.setToken(token);
            return ResponseEntity.ok(responseToken);
        } else {
            return ResponseEntity.badRequest().body("Mã lỗi 400: Email đăng nhập không hợp lệ hoặc password không đúng");
        }
    }

    /*@params ForgetForm forgetForm: dữ liệu lấy từ client để đổi mật khẩu bao gồm email, mật khẩu, nhập lại mật khẩu
     *Output: trả về thông báo tương ứng
     */
    @PutMapping("/forget")
    public ResponseEntity<String> updatePassword(@RequestBody ForgetForm forgetForm){
        try {
            // Kiểm tra xác nhận email và các điều kiện khác
            if (!userService.checkFormatEmail(forgetForm.getEmail().trim())
                    || !userService.checkPassword(forgetForm.getPassword().trim(), forgetForm.getRePassword().trim())
                    //Check email  trong DB (trường hợp này trả về True có nghĩa là email này không tồn tại trong DB
                    || userService.checkEmail(forgetForm.getEmail().trim())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Mật khẩu không khớp hoặc email không đúng quy định");
            }
            // Đổi mật khẩu
            userService.updatePassword(forgetForm);
            return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        } catch (Exception e) {
            // Xử lý ngoại lệ tại đây
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Có lỗi xảy ra trong quá trình cập nhật mật khẩu");
        }

    }
    /*
    Lấy 5 chuyên khoa được nhiều người đặt lịch nhất
     */

    @GetMapping("/topfivespecialization")
    public ResponseEntity<?>getTopspecializations(){
        try {
            //top 5 chuyên khoa được đặt lịch nhiều nhất
            List<Specialization> list = specializationService.getTopspecializations();
            return ResponseEntity.ok(list); // Trả về danh sách chuyên khoa nếu thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy danh sách chuyên khoa"); // Trả về mã lỗi 500 nếu có lỗi xảy ra
        }
    }

    @GetMapping("/getTopClinic")
    public ResponseEntity<?>getTopClinic(){
        try {
            List<Clinic> clinicList = clinicService.getTopClinic();
            return ResponseEntity.ok(clinicList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy danh sách phòng khám");
        }
    }

     /*
         lấy thông tin cá nhân của user và lịch sử khám bệnh
         @Param header : là token của user
     */

   // RespondeProfileUser respondeProfileUser = new RespondeProfileUser();
    @GetMapping("/PersonalInformation")
    public ResponseEntity<?>getProfileUser(@RequestHeader("Authorization") String token){
        try {
            ResponseProfileUser respondeProfileUser = new ResponseProfileUser();
            //Lấy thông tin user sử dụng idUser trong token mà User đã đăng nhập
            String[]split = token.split(" ");
            int userId = Integer.parseInt(JwtUtil.getUserIdFromToken(split[1]));
            User user = userService.getUserById(userId);
            respondeProfileUser.setUser(user);
            //Lấy lịch sử khám bệnh
            List<Schedule> scheduleList= scheduleService.getHistoryOfUserId(userId);
            respondeProfileUser.setListHistoryOfPatient(scheduleList);
            return ResponseEntity.ok(respondeProfileUser);

        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy thông tin");
        }
    }

    /*
    *Tìm kiếm theo chuyên khoa, cơ sở y tế
    * @String query : Nhận từ client từ khóa để tìm kiếm
    * Output: Danh dách chuyên khoa và bệnh nhân
     */
    @GetMapping("/generalSearch")
    public ResponseEntity<?>getSearch(@RequestParam("query") String query){
        try {
            GeneralSearch generalSearch = new GeneralSearch();
            generalSearch.setSpecializationList(specializationService.getResultSpecializations(query.trim().toLowerCase()));
            generalSearch.setClinicList(clinicService.getResultClinics(query.trim().toLowerCase()));
            return ResponseEntity.ok(generalSearch);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy thông tin");
        }
    }

    /*
     *Tìm kiếm theo chuyên khoa, cơ sở y tế
     * @String query : Nhận từ client từ khóa để tìm kiếm
     * Output : Danh sách các bác sỹ theo chuyên khoa mà user tìm kiếm
     */
    @GetMapping("/searchDoctor")
    public ResponseEntity<?>getSearchDoctor(@RequestParam("query") String query){
        try {
            SearchDoctorBySpecialization searchDoctorBySpecialization = new SearchDoctorBySpecialization();
            searchDoctorBySpecialization.setDoctorUserList(doctorService.
                    getResultDoctorBySpecialization(query.toLowerCase().trim()));
            return ResponseEntity.ok(searchDoctorBySpecialization);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy thông tin");
        }
    }

    //Chức năng naày đối với Bs và Admin khi login vẫn có thể su dụng được
    @PostMapping("/creatASchedule")
    public ResponseEntity<?>getSearchDoctor(@RequestBody ScheduleForm scheduleForm,@RequestHeader("Authorization") String token){
        try {
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Token không hợp lệ.");
            }

            String[] split = token.split(" ");
            if (split.length != 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Token không hợp lệ.");
            }
            String userIdString = JwtUtil.getUserIdFromToken(split[1]);

            //Kiểm tra bác sỹ nhận khám có tồn tại hay không
            DoctorUser doctorUser = doctorService.isExisted(scheduleForm.getIdDoctor());
            if(doctorUser!=null){
                //Xử lý tiếp Tạo mới 1 schedule
                scheduleService.creatASchedule(scheduleForm,doctorUser,userService.getUserById(Integer.parseInt(userIdString)));
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã lỗi 400: Bác sỹ này không tồn tại.");


            return ResponseEntity.ok("Đã tạo lịch khám thành công");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã lỗi 500: Lỗi khi lấy thông tin");
        }
    }


}