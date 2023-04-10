package com.group.booking.Common;

public class Message {
    
    public static final String SELECT_SUCCESS = "Lấy dữ liệu thành công";
    public static final String SELECT_FAILED = "Lấy dữ liệu thất bại";
    public static final String UPDATE_SUCCESS = "Cập nhật dữ liệu thành công";
    public static final String UPDATE_FAILED = "Cập nhật dữ liệu thất bại";
    public static final String INSERT_SUCCESS = "Thêm dữ liệu thành công";
    public static final String INSERT_FAILED = "Thêm dữ liệu thất bại";

    public static final String USERNAME_UNIQUE = "Tên tài khoản này đã được sử dụng";
    public static final String PHONE_UNIQUE = "Số điện thoại này đã được sử dụng";
    public static final String EMAIL_UNIQUE = "Email này đã được sử dụng";
    public static final String NAME_UNIQUE = "Tên này đã được sử dụng";

    public static final String USERNAME_VALIDATE = "Tên tài khoản chỉ chứa 6 - 20 chữ và số";
    public static final String PASSWORD_VALIDATE = "Mật khẩu chỉ chứa 6 - 20 chữ và số";
    public static final String FIRSTNAME_VALIDATE = "Họ không hợp lệ";
    public static final String LASTNAME_VALIDATE = "Tên không hợp lệ";
    public static final String EMAIL_VALIDATE = "Email không hợp lệ";
    public static final String PHONE_VALIDATE = "Số điện thoại phải chứa 10 số";

    public static final String GENDER_VALIDATE = "Giới tính không hợp lệ";
    public static final String PRICE_VALIDATE = "Giá phải lơn hơn hoặc bằng 200k";
    public static final String QUANTITY_VALIDATE = "Số lượng phải lớn hơn 0";

    public static final String USERNAME_NOT_EMP = "Tên tài khoản không được bỏ trống";
    public static final String PASSWORD_NOT_EMP = "Mật khẩu không được bỏ trống";
    public static final String FIRSTNAME_NOT_EMP = "Họ không được bỏ trống";
    public static final String LASTNAME_NOT_EMP = "Tên không được bỏ trống";
    public static final String BIRTHDAY_NOT_EMP = "Ngày sinh không được bỏ trống";
    public static final String EMAIL_NOT_EMP = "Email không được bỏ trống";

    public static final String PHONE_NOT_EMP = "Số điện thoại không được bỏ trống";
    public static final String GENDER_NOT_EMP = "Giới tính không được bỏ trống";
    public static final String NAME_NOT_EMP = "Tên không được bỏ trống";
    public static final String ADDRESS_NOT_EMP = "Địa chỉ không được bỏ trống";
    public static final String CHECKIN_NOT_EMP = "Địa chỉ không được bỏ trống";
    public static final String CHECKOUT_NOT_EMP = "Địa chỉ không được bỏ trống";

    public static final String LAT_NOT_EMP = "Kinh độ không được bỏ trống";
    public static final String LON_NOT_EMP = "Vĩ độ không được bỏ trống";
    public static final String DESCRIPTION_NOT_EMP = "Nội dung mô tả không được bỏ trống";
    public static final String AVATAR_NOT_EMP = "Ảnh đại diện không được bỏ trống";
    public static final String PRICE_NOT_EMP = "Ảnh đại diện không được bỏ trống";
    public static final String QUANTITY_NOT_EMP = "Ảnh đại diện không được bỏ trống";
    public static final String ID_NOT_EMPTY = "Mã không được bỏ trống";
    
    public static final String BAD_REQUEST = "Cú pháp không hợp lệ";
    public static final String UNAUTHORIZED = "Bạn cần phải đăng nhập";
    public static final String FORBIDDEN = "Không có quyền truy cập";
    public static final String NOT_FOUND = "Trang bạn tìm không tồn tại";
    public static final String REQUEST_TIMEOUT = "Hết thời gian kết nối";

    public static final String SIGNIN_SUCCESS = "Đăng nhập thành công";
    public static final String SIGNIN_FAILED = "Tài khoản hoặc mật khẩu sai";
    public static final String SIGNUP_SUCCESS = "Tạo tài khoản mới thành công";
    public static final String SIGNUP_FAILED = "Tạo tài khoản mới thất bại";
    public static final String CHANGE_PASSWORD_SUCCESS = "Thay đổi mật khẩu thành công";

    public static final String SENT_EMAIL_SUCCESS = "Hãy kiểm tra email để lấy lại mật khẩu !!";
    public static final String SENT_EMAIL_FAILED = "Email chưa đăng ký hoặc hãy thử lại sau 10 Phút !!";
    public static final String ID_NAME_UNIQUE = "Mã hoặc Tên thành phố này đã được sử dụng !!";
    public static final String SIZE_VALIDATE = "Kích thước Hình ảnh tối đa 15MB";

}
