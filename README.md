# demoConsoleApp

## Khách hàng 
```
 - id: mã số theo định dạng <ngay><thang><nam>sothutu4chuso (120820229898)
 - họ tên
 - giới tính
 - ngày
 - sinh
 - quê quán
 - số căn cước công dân
 - số tiền gửi (tối thiểu 50 nghìn VNĐ) 
```
## Tài khoản
```
 - interestRate
 
```
có không kỳ hạn (lãi suất 0.2%/năm) và được cung
cấp một tài khoản đăng nhập hệ thống. Khi đó, khách hàng có thể sử dụng các dịch vụ khác
của ngân hàng và có thể mở nhiều tài khoản có kỳ hạn khác.


Một tài khoản có kỳ hạn bao gồm thông tin kỳ hạn (1 tuần, 1 tháng, 12 tháng, …) và thông
tin, lãi suất nhận lúc mở tài khoản, hệ thống tự tính ngày đáo hạn khi khách hàng mở tài
khoản. Khách hàng có thể gửi tiền/rút tiền theo vào tài khoản có kỳ hạn đưa vào tài khoản
thanh toán, nhưng chỉ được phép gửi/rút vào ngày đáo hạn. Nếu khách hàng muốn rút tiền
trước hạn thì khách hàng chỉ được nhận lãi suất không kỳ hạn cho đến thời điểm rút.

Giả sử biểu mẫu lãi suất các kỳ hạn hiện tại như sau: (ngân hàng có thể dễ dàng mở rộng
các loại kỳ hạn mới)
Kỳ hạn Lãi suất (5/năm)
1 tuần 2%
1 tháng 5.5%
6 tháng 7.5%
12 tháng 7.9%
Viết chương trình thực hiện các chức năng sau:
- Nhân viên mở tài khoản cho khách hàng và cung cấp username và password cho khách
  hàng. Trong đó username là mã số khách hàng được khách hàng cấp và mật khẩu là số
  ngẫu nhiên có 6 chữ số và khách hàng có thể đổi sau đó.
- Khách hàng đăng nhập và mở thêm tài khoản có kỳ hạn, số tiền tối thiểu để mở tài
  khoản là 100 nghìn VNĐ và đảm bảo tài khoản chính còn tối thiểu 50 nghìn VNĐ.
- Tính tiền lãi nhận được cho khách hàng dựa trên số tài khoản cung cấp.
- Khách hàng gửi tiền/rút tiền vào tài khoản chính (tài khoản không kỳ hạn)
  BÀI TẬP LỚN MÔN LẬP TRÌNH OOP VỚI JAVA DƯƠNG HỮU THÀNH – KHOA CNTT, ĐẠI HỌC MỞ TP.HCM
  14
- Xử lý khách hàng rút tiền tài khoản có kỳ hạn trước ngày đáo hạn của tài khoản có kỳ
  hạn, trả lại tiền và tiền lãi (theo không kỳ hạn) vào tài khoản chính của khách hàng.
- Tra cứu khách hàng theo họ tên và mã số khách hàng.
- Tra cứu danh sách tài khoản của một khách hàng theo mã số khách hàng.
- Sắp xếp danh sách khách hàng có tổng số tiền gửi giảm dần.
  Yêu cầu thiết kế menu cho người dùng chọn thực hiện, thiết kế chương trình sao cho dễ
  dàng mở rộng chương trình.