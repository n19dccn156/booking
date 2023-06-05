
# Môn Phát triển phần mềm hướng dịch vụ
# Giảng viên: Huỳnh Thanh Trụ

# Đồ án: Booking phòng khách sạn
### Thành viên nhóm:

- [@Nguyễn Thanh Sang - N19DCCN156](https://www.github.com/n19dccn156)
- [@Phạm Hoàng Việt - N19DCCN226](https://www.github.com/vietpham2310)
- [@Trương Hoàng Sang - N19DCCN157]()
- [@Phạm Hữu Ngân Phương - N19DCCN149]()
- [@Nguyễn Hoàng Việt - N19DCCN224]()


# Setup projects:
- Setup database (Mysql 8.0.32)
```bash
  download file https://github.com/n19dccn156/booking/blob/master/booking.sql

  import file vào trong csdl
```

- Setup backend (Nên sử dụng intellij để không phải config: java 11)
```bash
  git clone https://github.com/n19dccn156/booking.git

  cd booking/src/main/java/com/group/booking
  
  sửa file booking/src/main/resources/application.properties cho phù hợp với kết nối db.
  spring.datasource.username=root
  spring.datasource.password=N19dccn#

  Chạy file BookingApplication.java

  Swagger: http://localhost/swagger-ui.html
```

- Setup web (node -v 19)
```bash
  git clone https://github.com/n19dccn156/booking-manage.git

  cd booking-manage

  npm i (yarn)

  npm run start

  http://localhost:3000
```

- Setup mobile:
```bash
git clone https://github.com/vietpham2310/Hotel-Booking-mobile.git

cd Hotel-Booking-mobile

Sửa lại IP local: Hotel-Booking-mobile/blob/main/app/src/main/java/com/example/hotelbooking/constant/Constant.java

chọn file build.gradle và download library

Chạy project
```
