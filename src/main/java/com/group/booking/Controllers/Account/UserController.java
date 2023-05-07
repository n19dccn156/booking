package com.group.booking.Controllers.Account;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Account.UserImpl;
import com.group.booking.Models.Account.ChangePasswordModel;
import com.group.booking.Models.Account.SignInModel;
import com.group.booking.Models.Account.SignUpModel;
import com.group.booking.Models.Account.UserModel;
import com.group.booking.Models.Account.UserResponse;
import com.group.booking.Models.Account.UserUpdate;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Services.Account.UserService;
import com.group.booking.Services.Image.ImageService;
import com.group.booking.Utils.JwtUltil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
@Api(tags = "User")
public class UserController implements UserImpl {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUltil jwtUltil;
    @Autowired
    private ImageService imageService;

    @Override
    @PostMapping("/signup")
    @ApiOperation(value = "Signup [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> signup(@RequestBody @Validated SignUpModel model) {
        UserResponse user = userService.signup(model);
        if(user != null) {
            if(user.getId() == -1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(Const.STATUS_FAILED, user.getEmail(), "")
                );
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            headers.add("Authorization", "Bearer " + jwtUltil.generateAccessToken(String.valueOf(user.getId())));
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SIGNUP_SUCCESS, user)
            );
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject(Const.STATUS_FAILED, Message.SIGNUP_FAILED, "")
        ); 
    }

    @Override
    @PostMapping("/signin")
    @ApiOperation(value = "Signing [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> signing(@RequestBody @Validated SignInModel model) {
        UserResponse user = userService.signing(model);
        if(user != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            headers.add("Authorization", "Bearer " + jwtUltil.generateAccessToken(String.valueOf(user.getId())));
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SIGNIN_SUCCESS, user)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject(Const.STATUS_FAILED, Message.SIGNIN_FAILED, "")
        ); 
    }

    @Override
    @PutMapping("/forgot/{email}")
    @ApiOperation(value = "Forgot password [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> forgot(@PathVariable("email") String email) {
        return userService.forgot(email.toLowerCase().trim()) ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SENT_EMAIL_SUCCESS, true)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SENT_EMAIL_FAILED, false)
            );
    }

    @Override
    @GetMapping("/{id}/reset")
    @ApiOperation(value = "Reset password [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> reset(@PathVariable("id") int userId) {
        
        String data = userService.reset(userId);
        return data.length() == 6 ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, data)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, "")
            );
            
    }

    @Override
    @PutMapping("/change-password")
    @ApiOperation(value = "Chang Password [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> changePassword(@RequestBody @Validated ChangePasswordModel model) {
        String isSuccess = userService.changePassword(model);
        return isSuccess.equals("OK") ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.CHANGE_PASSWORD_SUCCESS, true)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, isSuccess, false)
            );
    }
   
    @Override
    @GetMapping("")
    @ApiOperation(value = "Get info of User [Authentication]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getByAuthorization(HttpServletRequest request) {
        UserModel foundUser = userService.findByAuthorization(request.getHeader("Authorization"));
        return foundUser != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundUser)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.NOT_FOUND, "")
            );
    }

    @Override
    @GetMapping("/authorization")
    @ApiOperation(value = "get roleId by header authorization [Authentication]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getRoleIdByAuthorization(HttpServletRequest request) {
        String RoleId = userService.authorization(request.getHeader("Authorization"));
        return !RoleId.equals("") ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, RoleId)
            )
            :
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UNAUTHORIZED, RoleId)
            );
    }

    @Override
    @PatchMapping("")
    @ApiOperation(value = "update by header authorization [Authentication]", consumes = "application/json")
    public ResponseEntity<ResponseObject> updateInfoByAuthorization(@RequestBody @Validated UserUpdate user, HttpServletRequest request) {
        String message = userService.updateInfo(user, request.getHeader("Authorization"));
        return message != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, message, user)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.NOT_FOUND, user)
            );
    }
    
    @Override
    @PatchMapping("/avatar/base64")
    @ApiOperation(value = "update avatar by base64 and header authorization [Authentication]", consumes = "application/json")
    public ResponseEntity<ResponseObject> updateAvatarBase64(@RequestParam(name = "urlAvatarOld", required = true) String urlImgOld, @RequestParam(name = "urlBase64", required = true) String base64, HttpServletRequest request) {
        String avatar = imageService.putImage(urlImgOld, Base64.getDecoder().decode(base64), request.getHeader("Authorization"));

        return !avatar.equals("") ? 
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, avatar)
            )
            :
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, avatar)
            );
    }

    @Override
    @PatchMapping("/forgot")
    @ApiOperation(value = "update PASSWORD for forgot password [Authentication]", consumes = "application/json")
    public ResponseEntity<ResponseObject> updateForgotPassword(HttpServletRequest request, @RequestParam(name = "password1", required = true) String password1,
            @RequestParam(name = "password2", required = true) String password2) {
        String message = userService.updateForgotPassword(request.getHeader("Authorization"), password1, password2);
        if(message.equals("OK"))
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, "Cập nhật mật khẩu thành công", true)
            );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject(Const.STATUS_FAILED, message, false)
        );
    }
    
    
}
