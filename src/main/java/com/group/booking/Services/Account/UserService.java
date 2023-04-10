package com.group.booking.Services.Account;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Models.Account.ChangePasswordModel;
import com.group.booking.Models.Account.RoleModel;
import com.group.booking.Models.Account.SignInModel;
import com.group.booking.Models.Account.SignUpModel;
import com.group.booking.Models.Account.UserModel;
import com.group.booking.Models.Account.UserResponse;
import com.group.booking.Repositories.Account.UserRepository;
import com.group.booking.Utils.JwtUltil;
import com.group.booking.Utils.TimeUltil;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtUltil jwtUltil;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Environment env;

    public UserResponse signup(SignUpModel model) {
        try {
            model.preProcessing();
            UserModel user = new UserModel(model);
            user.setActive(true);
            user.setRoleId(Const.ROLE_CUSTOMER);
            user.setPassword(encoder.encode(model.getPassword()));

            List<UserModel> checkUnique = userRepository.findByUsernameOrEmailOrPhone(model.getUsername(), model.getEmail(), model.getPhone());
            if(checkUnique.isEmpty()) {
                user = userRepository.save(user);
                RoleModel role = roleService.foundById(user.getRoleId());
                return new UserResponse(user, role.getName());
            }
            else {
                UserResponse usr = new UserResponse();
                usr.setId(-1);
                if(checkUnique.get(0).getUsername().equals(model.getUsername())) {
                    usr.setEmail(Message.USERNAME_UNIQUE);
                } else if(checkUnique.get(0).getEmail().equals(model.getEmail())) {
                    usr.setEmail(Message.EMAIL_UNIQUE);
                } else {
                    usr.setEmail(Message.PHONE_UNIQUE);
                }
                return usr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponse signing(SignInModel model) {
        try {
            model.setUsername(model.getUsername().toLowerCase());
            UserModel foundUser = foundUserByUsername(model.getUsername());
            if(foundUser != null && foundUser.isActive() && encoder.matches(model.getPassword(), foundUser.getPassword())) {
                RoleModel role = roleService.foundById(foundUser.getRoleId());
                if(role != null) {
                    return new UserResponse(foundUser, role.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean forgot(String email) {
        try {
            UserModel foundUser = foundUserByEmail(email);
            if(foundUser != null) {
                if(foundUser.getVerify() != null) {
                    String userId = jwtUltil.validateAndGetSubject(foundUser.getVerify());
                    if(userId.length() == 0) return false;
                }
                
                String verify = jwtUltil.generateVerify(email);

                foundUser.setVerify(verify);
                userRepository.save(foundUser);
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                String css = "background-color: #4CAF50;"+
                    "border: none;"+
                    "color: white;"+
                    "padding: 16px 32px;"+
                    "text-align: center;"+
                    "text-decoration: none;"+
                    "display: inline-block;"+
                    "font-size: 16px;"+
                    "margin: 4px 2px;"+
                    "transition-duration: 0.4s;"+
                    "cursor: pointer;"+
                    "border-radius: 30px";
                String h3 = "<h3>Chào "+foundUser.getLastname()+",</h3><br>";
                String text1 = "<p>Chúng tôi nhận được yêu cầu đặt lại mật khẩu đến email này từ <b>"+foundUser.getLastname()+"</b></p><br>";
                String text2 = "<p>Để đặt lại mật khẩu, hãy nhấn nút ở phía dưới.</p><br>";
                String btn = "<a href=\""+Const.HOST+"/api/v1/users/"+foundUser.getId()+"/reset"+"\" target=\"_blank\"><button style=\""+css+"\">Đặt lại mật khẩu</button></a>";
                String htmlMsg = h3+ text1 + text2 + btn;

                helper.setText(htmlMsg, true); // Use this or above line.
                helper.setFrom(env.getProperty("spring.mail.username"));
                helper.setTo(email);
                helper.setSubject("Cập nhật mật khẩu mới");
                emailSender.send(mimeMessage);
                
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String reset(int userId) {
        try {
            UserModel foundUser = foundUserById(userId);
            if(foundUser != null && foundUser.getVerify().length() > 0) {
                String id = jwtUltil.validateAndGetSubject("Bearer " + foundUser.getVerify());
                if(id.length() != 0) {
                    Long password = TimeUltil.getCurrentTimeMillis() % 999999;
                    foundUser.setVerify(null);
                    foundUser.setPassword(encoder.encode(String.valueOf(password)));
                    userRepository.save(foundUser);

                    return String.valueOf(password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String changePassword(ChangePasswordModel model) {
        try {
            if(model.getNewPassword().trim().equals(model.getNewPassword2().trim())) {
                UserModel foundUser = foundUserById(model.getUserId());
                
                if(foundUser != null && encoder.matches(model.getOldPassword(), foundUser.getPassword())) {
                    foundUser.setPassword(encoder.encode(model.getNewPassword()));
                    userRepository.save(foundUser);
                    return "OK";
                }
                return "Mật khẩu cũ không đúng";
            }
            return "Mật khẩu nhập lại không khớp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.UPDATE_FAILED;
    }

    public UserModel findByIdAndAuthorization(int userId, String authorization) {
        try {
            String id = jwtUltil.validateAndGetSubject(authorization);
            if(userId == Integer.valueOf(id)) {
                UserModel foundUser = foundUserById(userId);
                
                return foundUser != null ? foundUser : null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel foundUserByUsername(String username) {
        try {
            Optional<UserModel> foundUser = userRepository.findByUsername(username);
            return foundUser.isPresent() ? foundUser.get() : null; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserModel foundUserById(int id) {
        try {
            Optional<UserModel> foundUser = userRepository.findById(id);
            return foundUser.isPresent() ? foundUser.get() : null;   
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserModel foundUserByEmail(String email) {
        try {
            Optional<UserModel> foundUser = userRepository.findByEmail(email.trim());
            return foundUser.isPresent() ? foundUser.get() : null;   
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
